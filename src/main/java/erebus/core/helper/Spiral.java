package erebus.core.helper;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Spiral {

	private enum Direction {
		E(1, 0),
		N(0, 1),
		W(-1, 0),
		S(0, -1);

		private final int dx;
		private final int dy;

		Direction(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}

		Point advance(Point point) {
			return new Point(point.x + dx, point.y + dy);
		}

		public static Direction next(Direction dir) {
			int ordinal = dir.ordinal() + 1;
			if (ordinal >= values().length)
				ordinal = 0;

			return values()[ordinal];
		}
	};

	private final int width;
	private final int length;
	private Point point;
	private Direction direction = Direction.E;
	private final List<Point> list = new ArrayList<Point>();

	public Spiral(int width, int length) {
		this.width = width;
		this.length = length;
	}

	public List<Point> spiral() {
		point = new Point(0, 0);
		int steps = 1;
		while (list.size() < width * length) {
			advance(steps);
			advance(steps);
			steps++;
		}
		return list;
	}

	private void advance(int step) {
		for (int i = 0; i < step; i++) {
			if (inBounds(point))
				list.add(point);
			point = direction.advance(point);
		}
		direction = Direction.next(direction);
	}

	private boolean inBounds(Point p) {
		return between(-width / 2, width / 2, p.x) && between(-length / 2, length / 2, p.y);
	}

	private boolean between(int min, int max, int value) {
		return min <= value && value <= max;
	}
}