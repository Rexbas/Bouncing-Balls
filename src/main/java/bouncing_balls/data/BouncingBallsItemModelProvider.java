package bouncing_balls.data;

import bouncing_balls.BouncingBalls;
import bouncing_balls.init.BouncingBallsItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BouncingBallsItemModelProvider extends ItemModelProvider {

	public BouncingBallsItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, BouncingBalls.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		registerItemModels();		
	}
	
	private void registerItemModels() {
		ModelFile bouncingball = getExistingFile(modLoc("item/bouncingball"));
		
		BouncingBallsItems.ITEMS.getEntries().forEach((ball) -> {
			String name = ball.getId().getPath();
			getBuilder(name).parent(bouncingball).texture("layer0", "item/" + name);
		});
	}
}