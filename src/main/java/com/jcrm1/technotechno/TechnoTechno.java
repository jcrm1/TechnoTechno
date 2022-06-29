package com.jcrm1.technotechno;

import java.util.List;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod(modid = TechnoTechno.MODID, version = TechnoTechno.VERSION, clientSideOnly = true)
public class TechnoTechno
{
    public static final String MODID = "technotechno";
    public static final String VERSION = "1.0";
    public static final ResourceLocation skin = new ResourceLocation("technotechno", "skin.png");
    public static final String skinType = new String("default");
    public static final String displayName = new String("Technoblade");
    public static String playerInfoFieldName;
    public static String locationSkinFieldName;
    public static String skinTypeFieldName;
    private boolean fullyLoaded = false;
    
    public boolean isFullyLoaded() {
    	return fullyLoaded;
    }
    public void markAsFullyLoaded() {
    	fullyLoaded = true;
    }
    @SideOnly(Side.CLIENT)
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(new SubscribeRenderPlayerPre(this));
    	MinecraftForge.EVENT_BUS.register(new SubscribeNameFormat());
    	MinecraftForge.EVENT_BUS.register(new SubscribeRenderHand());
    }
    @SideOnly(Side.CLIENT)
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	List<Field> acpFields = Arrays.asList(AbstractClientPlayer.class.getDeclaredFields());
    	List<String> acpStrings = new ArrayList<String>();
    	for (Field field : acpFields) {
    		acpStrings.add(field.toString());
    	}
    	if (acpStrings.contains("private net.minecraft.client.network.NetworkPlayerInfo net.minecraft.client.entity.AbstractClientPlayer.field_175157_a")) playerInfoFieldName = "field_175157_a";
    	else playerInfoFieldName = "playerInfo";
    	List<Field> npiFields = Arrays.asList(NetworkPlayerInfo.class.getDeclaredFields());
    	List<String> npiStrings = new ArrayList<String>();
    	for (Field field : npiFields) {
    		npiStrings.add(field.toString());
    	}
    	if (npiStrings.contains("private net.minecraft.util.ResourceLocation net.minecraft.client.network.NetworkPlayerInfo.field_178865_e")) locationSkinFieldName = "field_178865_e";
    	else locationSkinFieldName = "locationSkin";
    	
    	List<Field> stFields = Arrays.asList(NetworkPlayerInfo.class.getDeclaredFields());
    	List<String> stStrings = new ArrayList<String>();
    	for (Field field : stFields) {
    		stStrings.add(field.toString());
    	}
    	if (stStrings.contains("private java.lang.String net.minecraft.client.network.NetworkPlayerInfo.field_178863_g")) skinTypeFieldName = "field_178863_g";
    	else skinTypeFieldName = "skinType";

        System.out.print("Loaded TechnoTechno! Welcome to the world of Techno");
    }
    public static void makeTechnoblade(AbstractClientPlayer player) {
    	try {
			Field playerInfoField = AbstractClientPlayer.class.getDeclaredField(TechnoTechno.playerInfoFieldName);
			playerInfoField.setAccessible(true);
			NetworkPlayerInfo networkPlayerInfo = (NetworkPlayerInfo) playerInfoField.get(player);
			Field locationSkinField = NetworkPlayerInfo.class.getDeclaredField(TechnoTechno.locationSkinFieldName);
			locationSkinField.setAccessible(true);
			locationSkinField.set(networkPlayerInfo, TechnoTechno.skin);
			Field skinTypeField = NetworkPlayerInfo.class.getDeclaredField(TechnoTechno.skinTypeFieldName);
			skinTypeField.setAccessible(true);
			skinTypeField.set(networkPlayerInfo, TechnoTechno.skinType);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
