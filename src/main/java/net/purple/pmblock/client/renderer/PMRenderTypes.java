package net.purple.pmblock.client.renderer;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.purple.pmblock.client.ClientSetup;

public class PMRenderTypes {


    public static RenderType hologram() {
        return Internal.hologram();
    }

    private static class Internal extends RenderType{

        public Internal(String pName, VertexFormat pFormat, VertexFormat.Mode pMode, int pBufferSize, boolean pAffectsCrumbling, boolean pSortOnUpload, Runnable pSetupState, Runnable pClearState) {
            super(pName, pFormat, pMode, pBufferSize, pAffectsCrumbling, pSortOnUpload, pSetupState, pClearState);
        }

        private static RenderType hologram(){
            RenderType.CompositeState renderState = RenderType.CompositeState.builder()
                    .setShaderState(new ShaderStateShard(ClientSetup::getHologramShader))
                    .setLightmapState(LIGHTMAP)
                    .setTextureState(RenderStateShard.BLOCK_SHEET_MIPPED)
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setOutputState(TRANSLUCENT_TARGET)
                    .createCompositeState(true);
            return create("hologram", DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS, 2097152, true,true,renderState);
        }
    }
}
