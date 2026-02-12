package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.WoodlouseRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class WoodlouseModel extends EntityModel<WoodlouseRenderState> {
    public ModelPart root;
    private final ModelPart AntBaseR, AntBaseL, AntR1, AntR2, AntL1, AntL2;
    private final ModelPart HL1, HR2, HR1, HL2, HM1, HM2;
    private final ModelPart ThxR2, ThxL2, ThxR3, ThxL3, ThxR4, ThxL4, ThxR5, ThxL5, ThxR6, ThxL6, ThxR7, ThxL7, ThxR8, ThxL8, ThxR9, ThxL9, ThxR10, ThxL10, Thx11, Thx12;
    private final ModelPart RLA1, RLA2, RLA3, RLB1, RLB2, RLB3, RLC1, RLC2, RLC3, RLD1, RLD2, RLD3, RLE1, RLE2, RLE3, RLF1, RLF2, RLF3, RLG1, RLG2, RLG3;
    private final ModelPart LLA1, LLA2, LLA3, LLB1, LLB2, LLB3, LLC1, LLC2, LLC3, LLD1, LLD2, LLD3, LLE1, LLE2, LLE3, LLF1, LLF2, LLF3, LLG1, LLG2, LLG3;

    public WoodlouseModel(ModelPart root) {
        super(root);
        this.root = root;
        this.AntBaseR = root.getChild("AntBaseR");
        this.AntBaseL = root.getChild("AntBaseL");
        this.AntR1 = root.getChild("AntR1");
        this.AntR2 = root.getChild("AntR2");
        this.AntL1 = root.getChild("AntL1");
        this.AntL2 = root.getChild("AntL2");
        this.HL1 = root.getChild("HL1");
        this.HR2 = root.getChild("HR2");
        this.HR1 = root.getChild("HR1");
        this.HL2 = root.getChild("HL2");
        this.HM1 = root.getChild("HM1");
        this.HM2 = root.getChild("HM2");
        this.ThxR2 = root.getChild("ThxR2");
        this.ThxL2 = root.getChild("ThxL2");
        this.ThxR3 = root.getChild("ThxR3");
        this.ThxL3 = root.getChild("ThxL3");
        this.ThxR4 = root.getChild("ThxR4");
        this.ThxL4 = root.getChild("ThxL4");
        this.ThxR5 = root.getChild("ThxR5");
        this.ThxL5 = root.getChild("ThxL5");
        this.ThxR6 = root.getChild("ThxR6");
        this.ThxL6 = root.getChild("ThxL6");
        this.ThxR7 = root.getChild("ThxR7");
        this.ThxL7 = root.getChild("ThxL7");
        this.ThxR8 = root.getChild("ThxR8");
        this.ThxL8 = root.getChild("ThxL8");
        this.ThxR9 = root.getChild("ThxR9");
        this.ThxL9 = root.getChild("ThxL9");
        this.ThxR10 = root.getChild("ThxR10");
        this.ThxL10 = root.getChild("ThxL10");
        this.Thx11 = root.getChild("Thx11");
        this.Thx12 = root.getChild("Thx12");

        this.RLA1 = root.getChild("RLA1");
        this.RLA2 = root.getChild("RLA2");
        this.RLA3 = root.getChild("RLA3");
        this.RLB1 = root.getChild("RLB1");
        this.RLB2 = root.getChild("RLB2");
        this.RLB3 = root.getChild("RLB3");
        this.RLC1 = root.getChild("RLC1");
        this.RLC2 = root.getChild("RLC2");
        this.RLC3 = root.getChild("RLC3");
        this.RLD1 = root.getChild("RLD1");
        this.RLD2 = root.getChild("RLD2");
        this.RLD3 = root.getChild("RLD3");
        this.RLE1 = root.getChild("RLE1");
        this.RLE2 = root.getChild("RLE2");
        this.RLE3 = root.getChild("RLE3");
        this.RLF1 = root.getChild("RLF1");
        this.RLF2 = root.getChild("RLF2");
        this.RLF3 = root.getChild("RLF3");
        this.RLG1 = root.getChild("RLG1");
        this.RLG2 = root.getChild("RLG2");
        this.RLG3 = root.getChild("RLG3");

        this.LLA1 = root.getChild("LLA1");
        this.LLA2 = root.getChild("LLA2");
        this.LLA3 = root.getChild("LLA3");
        this.LLB1 = root.getChild("LLB1");
        this.LLB2 = root.getChild("LLB2");
        this.LLB3 = root.getChild("LLB3");
        this.LLC1 = root.getChild("LLC1");
        this.LLC2 = root.getChild("LLC2");
        this.LLC3 = root.getChild("LLC3");
        this.LLD1 = root.getChild("LLD1");
        this.LLD2 = root.getChild("LLD2");
        this.LLD3 = root.getChild("LLD3");
        this.LLE1 = root.getChild("LLE1");
        this.LLE2 = root.getChild("LLE2");
        this.LLE3 = root.getChild("LLE3");
        this.LLF1 = root.getChild("LLF1");
        this.LLF2 = root.getChild("LLF2");
        this.LLF3 = root.getChild("LLF3");
        this.LLG1 = root.getChild("LLG1");
        this.LLG2 = root.getChild("LLG2");
        this.LLG3 = root.getChild("LLG3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("AntBaseR", CubeListBuilder.create().texOffs(117, 0).addBox(-1.5F, -0.5F, -2F, 2, 2, 3), PartPose.offset(-2F, 19.5F, -19F));
        root.addOrReplaceChild("AntBaseL", CubeListBuilder.create().texOffs(105, 0).addBox(-0.5F, -0.5F, -2F, 2, 2, 3), PartPose.offset(2F, 19.5F, -19F));
        root.addOrReplaceChild("AntR1", CubeListBuilder.create().texOffs(82, 33).addBox(-0.5F, -0.5F, -0.5F, 6, 1, 1), PartPose.offsetAndRotation(-3F, 20F, -21.33333F, 0F, 3.141593F, 0F));
        root.addOrReplaceChild("AntR2", CubeListBuilder.create().texOffs(80, 30).addBox(-0.5F, -0.5F, -0.5F, 8, 1, 1), PartPose.offsetAndRotation(-9F, 20F, -22F, 0F, 20.94395F, 0F));
        root.addOrReplaceChild("AntL1", CubeListBuilder.create().texOffs(82, 33).addBox(-0.5F, -0.5F, -0.5F, 6, 1, 1), PartPose.offset(3F, 20F, -21.33333F));
        root.addOrReplaceChild("AntL2", CubeListBuilder.create().texOffs(80, 30).addBox(-0.5F, -0.5F, -0.5F, 8, 1, 1), PartPose.offsetAndRotation(9F, 20F, -22F, 0F, 1.047198F, 0F));

        root.addOrReplaceChild("HL1", CubeListBuilder.create().texOffs(73, 18).addBox(-9.4F, -3.5F, 0F, 10, 8, 3), PartPose.offsetAndRotation(0F, 16F, -10.5F, -0.1745329F, 3.141593F, -0.1745329F));
        root.addOrReplaceChild("HR2", CubeListBuilder.create().texOffs(98, 8).addBox(-0.4F, -2F, 0F, 8, 7, 2), PartPose.offsetAndRotation(0F, 16F, -13.5F, -0.1745329F, -3.141593F, 0.1745329F));
        root.addOrReplaceChild("HR1", CubeListBuilder.create().texOffs(100, 18).addBox(-0.6F, -3.5F, 0F, 10, 8, 3), PartPose.offsetAndRotation(0F, 16F, -10.5F, -0.1745329F, -3.141593F, 0.1745329F));
        root.addOrReplaceChild("HL2", CubeListBuilder.create().texOffs(75, 8).addBox(-7.6F, -2F, 0F, 8, 7, 2), PartPose.offsetAndRotation(0F, 16F, -13.5F, -0.1745329F, 3.141593F, -0.1745329F));
        root.addOrReplaceChild("HM1", CubeListBuilder.create().texOffs(74, 0).addBox(-6.5F, 0F, 0F, 13, 5, 2), PartPose.offsetAndRotation(0F, 16F, -15.5F, -0.1745329F, 3.141593F, 0F));
        root.addOrReplaceChild("HM2", CubeListBuilder.create().texOffs(100, 31).addBox(-4.5F, 2F, 0F, 9, 3, 2), PartPose.offsetAndRotation(0F, 16F, -17.5F, -0.1745329F, 3.141593F, 0F));

        root.addOrReplaceChild("ThxR2", CubeListBuilder.create().texOffs(40, 0).addBox(-10.1F, -5F, 0F, 11, 9, 5), PartPose.offsetAndRotation(0F, 16F, -11F, 0F, 0F, -0.1745329F));
        root.addOrReplaceChild("ThxL2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9F, -5F, 0F, 11, 9, 5), PartPose.offsetAndRotation(0F, 16F, -11F, 0F, 0F, 0.1745329F));
        root.addOrReplaceChild("ThxR3", CubeListBuilder.create().texOffs(40, 15).addBox(-11F, -5.5F, 0F, 12, 10, 4), PartPose.offsetAndRotation(0F, 16F, -6F, 0F, 0F, -0.1745329F));
        root.addOrReplaceChild("ThxL3", CubeListBuilder.create().texOffs(0, 15).addBox(-1F, -5.5F, -6F, 12, 10, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 0F, 0.1745329F));
        root.addOrReplaceChild("ThxR4", CubeListBuilder.create().texOffs(44, 30).addBox(-11.9F, -6F, 0F, 13, 10, 5), PartPose.offsetAndRotation(0F, 16F, -2F, 0F, 0F, -0.1745329F));
        root.addOrReplaceChild("ThxL4", CubeListBuilder.create().texOffs(0, 30).addBox(-1.1F, -6F, 0F, 13, 10, 5), PartPose.offsetAndRotation(0F, 16F, -2F, 0F, 0F, 0.1745329F));
        root.addOrReplaceChild("ThxR5", CubeListBuilder.create().texOffs(44, 46).addBox(-11.8F, -6.5F, 0F, 13, 10, 5), PartPose.offsetAndRotation(0F, 16F, 3F, 0F, 0F, -0.1745329F));
        root.addOrReplaceChild("ThxL5", CubeListBuilder.create().texOffs(0, 46).addBox(-1.2F, -6.5F, 0F, 13, 10, 5), PartPose.offsetAndRotation(0F, 16F, 3F, 0F, 0F, 0.1745329F));
        root.addOrReplaceChild("ThxR6", CubeListBuilder.create().texOffs(44, 62).addBox(-11.9F, -6F, 0F, 13, 10, 6), PartPose.offsetAndRotation(0F, 16F, 8F, 0F, 0F, -0.1745329F));
        root.addOrReplaceChild("ThxL6", CubeListBuilder.create().texOffs(0, 62).addBox(-1.1F, -6F, 0F, 13, 10, 6), PartPose.offsetAndRotation(0F, 16F, 8F, 0F, 0F, 0.1745329F));
        root.addOrReplaceChild("ThxR7", CubeListBuilder.create().texOffs(44, 80).addBox(-11F, -5.5F, 0F, 12, 9, 5), PartPose.offsetAndRotation(0F, 16F, 14F, 0F, 0F, -0.1745329F));
        root.addOrReplaceChild("ThxL7", CubeListBuilder.create().texOffs(0, 80).addBox(-1F, -5.5F, 0F, 12, 9, 5), PartPose.offsetAndRotation(0F, 16F, 14F, 0F, 0F, 0.1745329F));
        root.addOrReplaceChild("ThxR8", CubeListBuilder.create().texOffs(44, 95).addBox(-10.1F, -5F, 0F, 11, 9, 5), PartPose.offsetAndRotation(0F, 16F, 19F, 0F, 0F, -0.1745329F));
        root.addOrReplaceChild("ThxL8", CubeListBuilder.create().texOffs(0, 95).addBox(-0.9F, -5F, 0F, 11, 9, 5), PartPose.offsetAndRotation(0F, 16F, 19F, 0F, 0F, 0.1745329F));
        root.addOrReplaceChild("ThxR9", CubeListBuilder.create().texOffs(73, 18).addBox(-9.4F, -3.5F, 0F, 10, 8, 3), PartPose.offsetAndRotation(0F, 16F, 23.5F, -0.1745329F, 0F, -0.1745329F));
        root.addOrReplaceChild("ThxL9", CubeListBuilder.create().texOffs(100, 18).addBox(-0.6F, -3.5F, 0F, 10, 8, 3), PartPose.offsetAndRotation(0F, 16F, 23.5F, -0.1745329F, 0F, 0.1745329F));
        root.addOrReplaceChild("ThxR10", CubeListBuilder.create().texOffs(75, 8).addBox(-7.6F, -2F, 0F, 8, 7, 2), PartPose.offsetAndRotation(0F, 16F, 26.5F, -0.1745329F, 0F, -0.1745329F));
        root.addOrReplaceChild("ThxL10", CubeListBuilder.create().texOffs(98, 8).addBox(-0.4F, -2F, 0F, 8, 7, 2), PartPose.offsetAndRotation(0F, 16F, 26.5F, -0.1745329F, 0F, 0.1745329F));
        root.addOrReplaceChild("Thx11", CubeListBuilder.create().texOffs(74, 0).addBox(-6.5F, 0F, 0F, 13, 5, 2), PartPose.offsetAndRotation(0F, 16F, 28.5F, -0.1745329F, 0F, 0F));
        root.addOrReplaceChild("Thx12", CubeListBuilder.create().texOffs(29, 114).addBox(-4.5F, 2F, 0F, 9, 3, 2), PartPose.offsetAndRotation(0F, 16F, 30.5F, -0.1745329F, 0F, 0F));

        float correction = 0.1745329F;
        float correction2 = 0.2268928F + correction;

        root.addOrReplaceChild("RLA1", CubeListBuilder.create().texOffs(82, 40).addBox(-1F, -1F, -0.5F, 5, 2, 2), PartPose.offsetAndRotation(-8F, 20F, -8F, 0F, 2.617994F, -0.8726646F + correction));
        root.addOrReplaceChild("RLA2", CubeListBuilder.create().texOffs(82, 50).addBox(2.5F, 0.7F, 0F, 5, 2, 1), PartPose.offsetAndRotation(-8F, 20F, -8F, 0F, 2.617994F, 0.2268928F - correction2));
        root.addOrReplaceChild("RLA3", CubeListBuilder.create().texOffs(84, 58).addBox(7F, 3F, 0F, 3, 1, 1), PartPose.offsetAndRotation(-8F, 20F, -8F, 0F, 2.617994F, 0.0174533F));

        root.addOrReplaceChild("RLB1", CubeListBuilder.create().texOffs(82, 40).addBox(-1F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(-8F, 20F, -3.5F, 0F, 2.879793F, -0.6981317F + correction));
        root.addOrReplaceChild("RLB2", CubeListBuilder.create().texOffs(82, 50).addBox(2.5F, 0.7F, -0.5F, 5, 2, 1), PartPose.offsetAndRotation(-8F, 20F, -3.5F, 0F, 2.879793F, 0.1745329F - correction2));
        root.addOrReplaceChild("RLB3", CubeListBuilder.create().texOffs(84, 58).addBox(7F, 3F, -0.5F, 3, 1, 1), PartPose.offsetAndRotation(-8F, 20F, -3.5F, 0F, 2.879793F, 0F));

        root.addOrReplaceChild("RLC1", CubeListBuilder.create().texOffs(82, 40).addBox(-1F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(-9F, 20F, 0.5F, 0F, 3.010693F, -0.6981317F + correction));
        root.addOrReplaceChild("RLC2", CubeListBuilder.create().texOffs(82, 50).addBox(2.5F, 0.7F, -0.5F, 5, 2, 1), PartPose.offsetAndRotation(-9F, 20F, 0.5F, 0F, 3.010693F, 0.1745329F - correction2));
        root.addOrReplaceChild("RLC3", CubeListBuilder.create().texOffs(84, 58).addBox(7F, 3F, -0.5F, 3, 1, 1), PartPose.offsetAndRotation(-9F, 20F, 0.5F, 0F, 3.010693F, 0F));

        root.addOrReplaceChild("RLD1", CubeListBuilder.create().texOffs(82, 40).addBox(-1F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(-10F, 20F, 5.5F, 0F, -3.141593F, -0.6981317F + correction));
        root.addOrReplaceChild("RLD2", CubeListBuilder.create().texOffs(82, 50).addBox(2.5F, 0.7F, -0.5F, 5, 2, 1), PartPose.offsetAndRotation(-10F, 20F, 5.5F, 0F, -3.141593F, 0.1745329F - correction2));
        root.addOrReplaceChild("RLD3", CubeListBuilder.create().texOffs(84, 58).addBox(7F, 3F, -0.5F, 3, 1, 1), PartPose.offsetAndRotation(-10F, 20F, 5.5F, 0F, -3.141593F, 0F));

        root.addOrReplaceChild("RLE1", CubeListBuilder.create().texOffs(82, 40).addBox(-1F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(-9F, 20F, 11F, 0F, -3.010693F, -0.6981317F + correction));
        root.addOrReplaceChild("RLE2", CubeListBuilder.create().texOffs(82, 50).addBox(2.5F, 0.7F, -0.5F, 5, 2, 1), PartPose.offsetAndRotation(-9F, 20F, 11F, 0F, -3.010693F, 0.1745329F - correction2));
        root.addOrReplaceChild("RLE3", CubeListBuilder.create().texOffs(84, 58).addBox(7F, 3F, -0.5F, 3, 1, 1), PartPose.offsetAndRotation(-9F, 20F, 11F, 0F, -3.010693F, 0F));

        root.addOrReplaceChild("RLF1", CubeListBuilder.create().texOffs(82, 40).addBox(-1F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(-8F, 20F, 16.5F, 0F, -2.879793F, -0.6981317F + correction));
        root.addOrReplaceChild("RLF2", CubeListBuilder.create().texOffs(82, 50).addBox(2.5F, 0.7F, -0.5F, 5, 2, 1), PartPose.offsetAndRotation(-8F, 20F, 16.5F, 0F, -2.879793F, 0.1745329F - correction2));
        root.addOrReplaceChild("RLF3", CubeListBuilder.create().texOffs(84, 58).addBox(7F, 3F, -0.5F, 3, 1, 1), PartPose.offsetAndRotation(-8F, 20F, 16.5F, 0F, -2.879793F, 0F));

        root.addOrReplaceChild("RLG1", CubeListBuilder.create().texOffs(82, 40).addBox(-2F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(-8F, 20F, 21.5F, 0F, -2.617994F, -0.8726646F + correction));
        root.addOrReplaceChild("RLG2", CubeListBuilder.create().texOffs(82, 50).addBox(1.5F, 0.7F, -0.5F, 5, 2, 1), PartPose.offsetAndRotation(-8F, 20F, 21.5F, 0F, -2.617994F, 0.2268928F - correction2));
        root.addOrReplaceChild("RLG3", CubeListBuilder.create().texOffs(84, 58).addBox(6F, 3F, -0.5F, 3, 1, 1), PartPose.offsetAndRotation(-8F, 20F, 21.5F, 0F, -2.617994F, 0.0174533F));

        root.addOrReplaceChild("LLA1", CubeListBuilder.create().texOffs(82, 40).addBox(-1F, -1F, -0.5F, 5, 2, 2), PartPose.offsetAndRotation(8F, 20F, -9F, 0F, 0.5235988F, 0.8726646F));
        root.addOrReplaceChild("LLA2", CubeListBuilder.create().texOffs(82, 50).addBox(2.5F, 0.7F, 0F, 5, 2, 1), PartPose.offsetAndRotation(8F, 20F, -9F, 0F, 0.5235988F, 0.2268928F));
        root.addOrReplaceChild("LLA3", CubeListBuilder.create().texOffs(84, 58).addBox(7F, 3F, 0F, 3, 1, 1), PartPose.offsetAndRotation(8F, 20F, -9F, 0F, 0.5235988F, 0.0174533F));

        root.addOrReplaceChild("LLB1", CubeListBuilder.create().texOffs(82, 40).addBox(-1F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(8F, 20F, -3.5F, 0F, 0.2617994F, 0.6981317F));
        root.addOrReplaceChild("LLB2", CubeListBuilder.create().texOffs(82, 50).addBox(2.5F, 0.7F, -0.5F, 5, 2, 1), PartPose.offsetAndRotation(8F, 20F, -3.5F, 0F, 0.2617994F, 0.1745329F));
        root.addOrReplaceChild("LLB3", CubeListBuilder.create().texOffs(84, 58).addBox(7F, 3F, -0.5F, 3, 1, 1), PartPose.offsetAndRotation(8F, 20F, -3.5F, 0F, 0.2617994F, 0F));

        root.addOrReplaceChild("LLC1", CubeListBuilder.create().texOffs(82, 40).addBox(-1F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(9F, 20F, 0.5F, 0F, 0.1308997F, 0.6981317F));
        root.addOrReplaceChild("LLC2", CubeListBuilder.create().texOffs(82, 50).addBox(2.5F, 0.7F, -0.5F, 5, 2, 1), PartPose.offsetAndRotation(9F, 20F, 0.5F, 0F, 0.1308997F, 0.1745329F));
        root.addOrReplaceChild("LLC3", CubeListBuilder.create().texOffs(84, 58).addBox(7F, 3F, -0.5F, 3, 1, 1), PartPose.offsetAndRotation(9F, 20F, 0.5F, 0F, 0.1308997F, 0F));

        root.addOrReplaceChild("LLD1", CubeListBuilder.create().texOffs(82, 40).addBox(-1F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(10F, 20F, 5.5F, 0F, 0F, 0.6981317F));
        root.addOrReplaceChild("LLD2", CubeListBuilder.create().texOffs(82, 50).addBox(2.5F, 0.7F, -0.5F, 5, 2, 1), PartPose.offsetAndRotation(10F, 20F, 5.5F, 0F, 0F, 0.1745329F));
        root.addOrReplaceChild("LLD3", CubeListBuilder.create().texOffs(84, 58).addBox(7F, 3F, -0.5F, 3, 1, 1), PartPose.offsetAndRotation(10F, 20F, 5.5F, 0F, 0F, 0F));

        root.addOrReplaceChild("LLE1", CubeListBuilder.create().texOffs(82, 40).addBox(-1F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(9F, 20F, 11F, 0F, -0.1308997F, 0.6981317F));
        root.addOrReplaceChild("LLE2", CubeListBuilder.create().texOffs(82, 50).addBox(2.5F, 0.7F, -0.5F, 5, 2, 1), PartPose.offsetAndRotation(9F, 20F, 11F, 0F, -0.1308997F, 0.1745329F));
        root.addOrReplaceChild("LLE3", CubeListBuilder.create().texOffs(84, 58).addBox(7F, 3F, -0.5F, 3, 1, 1), PartPose.offsetAndRotation(9F, 20F, 11F, 0F, -0.1308997F, 0F));

        root.addOrReplaceChild("LLF1", CubeListBuilder.create().texOffs(82, 40).addBox(-1F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(8F, 20F, 16.5F, 0F, -0.2617994F, 0.6981317F));
        root.addOrReplaceChild("LLF2", CubeListBuilder.create().texOffs(82, 50).addBox(2.5F, 0.7F, -0.5F, 5, 2, 1), PartPose.offsetAndRotation(8F, 20F, 16.5F, 0F, -0.2617994F, 0.1745329F));
        root.addOrReplaceChild("LLF3", CubeListBuilder.create().texOffs(84, 58).addBox(7F, 3F, -0.5F, 3, 1, 1), PartPose.offsetAndRotation(8F, 20F, 16.5F, 0F, -0.2617994F, 0F));

        root.addOrReplaceChild("LLG1", CubeListBuilder.create().texOffs(82, 40).addBox(-2F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(8F, 20F, 21.5F, 0F, -0.5235988F, 0.8726646F));
        root.addOrReplaceChild("LLG2", CubeListBuilder.create().texOffs(82, 50).addBox(1.5F, 0.7F, -0.5F, 5, 2, 1), PartPose.offsetAndRotation(8F, 20F, 21.5F, 0F, -0.5235988F, 0.2268928F));
        root.addOrReplaceChild("LLG3", CubeListBuilder.create().texOffs(84, 58).addBox(6F, 3F, -0.5F, 3, 1, 1), PartPose.offsetAndRotation(8F, 20F, 21.5F, 0F, -0.5235988F, 0.0174533F));

        return LayerDefinition.create(mesh, 128, 128);
    }
}
