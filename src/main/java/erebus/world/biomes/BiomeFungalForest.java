package erebus.world.biomes;

import erebus.world.biomes.decorators.BiomeDecoratorBaseErebus;
import erebus.world.biomes.decorators.BiomeDecoratorFungalForest;

public class BiomeFungalForest extends BiomeBaseErebus {
	public BiomeFungalForest(int biomeID) {
		this(biomeID, new BiomeDecoratorFungalForest());
	}

	@SuppressWarnings("unchecked")
	public BiomeFungalForest(int biomeID, BiomeDecoratorBaseErebus decorator) {
		super(biomeID, decorator);

		setBiomeName("Fungal Forest");
		setColors(0x4E8833);
		setTemperatureRainfall(0.9F, 0.95F);
		setWeight(12);
	}

}