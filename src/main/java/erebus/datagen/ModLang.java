package erebus.datagen;

import erebus.datagen.providers.ModLangProvider;
import net.minecraft.data.PackOutput;

public class ModLang extends ModLangProvider {
    public ModLang(PackOutput output) {
        super(output);
    }

    @Override
    protected void addTranslations() {
        addItemTranslations();
        addBlockTranslations();
        addEntityTranslations();
        addFluidTranslations();
        addCreativeTabTranslations();
        addToolTipTranslations();
        addBookTranslations();
        addAdvancementTranslations();
        addBiomeTranslations();
    }

    private void addItemTranslations() {

    }

    private void addBlockTranslations() {

    }

    private void addEntityTranslations() {

    }

    private void addFluidTranslations() {

    }

    private void addCreativeTabTranslations() {

    }

    private void addToolTipTranslations() {

    }

    private void addBookTranslations() {

    }

    private void addAdvancementTranslations() {

    }

    private void addBiomeTranslations() {

    }
}
