package erebus.network.data;

public record DeathCompassData(int x, int y, int z, String deathTime) {
    public DeathCompassData(int x, int y, int z) {
        this(x, y, z, "");
    }
}
