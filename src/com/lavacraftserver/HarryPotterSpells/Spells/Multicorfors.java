package com.lavacraftserver.HarryPotterSpells.Spells;

import java.util.HashSet;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import com.lavacraftserver.HarryPotterSpells.HPS;
import com.lavacraftserver.HarryPotterSpells.Spells.Spell.spell;
import com.lavacraftserver.HarryPotterSpells.Utils.Targeter;

@spell (
		name="Multicorfors",
		description="Changes the colour of any targeted wool or sheep",
		range=25,
		goThroughWalls=false
)
public class Multicorfors extends Spell{

	public void cast(Player p) {
		final Block b = p.getTargetBlock(transparentBlocks(), 25);
		if(Targeter.getTarget(p, this.getRange(), this.canBeCastThroughWalls()) instanceof Sheep) {
			final Sheep sheep = (Sheep) Targeter.getTarget(p, this.getRange(), this.canBeCastThroughWalls());
			if(HPS.Plugin.getConfig().getBoolean("spells.multicorfors.explosionEffect", true)) {
				sheep.getWorld().createExplosion(sheep.getLocation(), 0F);
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HPS.Plugin, new Runnable() {
				   public void run() {
					   sheep.setColor(randomDyeColor());
				   }
				}, 4L);
		} else if(b.getType() == Material.WOOL) {
			if(HPS.Plugin.getConfig().getBoolean("spells.multicorfors.explosionEffect", true)) {
				p.getWorld().createExplosion(b.getLocation(), 0F);
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HPS.Plugin, new Runnable() {
				   public void run() {
					   b.setData(randomDyeColorInt());
				   }
				}, 4L);
		} else {
			HPS.PM.warn(p, "You can only cast this spell on sheep or wool.");
		}
	}
	
	public DyeColor randomDyeColor() {
		DyeColor et;
		int maxMobs = 16, minMobs = 1;
		int randomNum = new Random().nextInt(maxMobs - minMobs + 1) + minMobs;
		
		switch(randomNum) {
		case 1:	et = DyeColor.BLACK;
				break;
		case 2:	et = DyeColor.BLUE;
				break;
		case 3: et = DyeColor.BROWN;
				break;
		case 4:	et = DyeColor.CYAN;
				break;
		case 5:	et = DyeColor.GRAY;
				break;
		case 6:	et = DyeColor.GREEN;
				break;
		case 7: et = DyeColor.LIGHT_BLUE;
				break;
		case 8:	et = DyeColor.LIME;
				break;
		case 9:	et = DyeColor.MAGENTA;
				break;
	    case 10: et = DyeColor.ORANGE;
				 break;
		case 11: et = DyeColor.PINK;
				 break;
		case 12: et = DyeColor.PURPLE;
				 break;
		case 13: et = DyeColor.RED;
				 break;
		case 14: et = DyeColor.SILVER;
				 break;
		case 15: et = DyeColor.WHITE;
				 break;
		case 16: et = DyeColor.YELLOW;
				 break;
		default: et = DyeColor.WHITE;
				 break;
		}
		return et;
	}
	
	public byte randomDyeColorInt() {
		int maxMobs = 16, minMobs = 1;
		int randomNum = new Random().nextInt(maxMobs - minMobs + 1) + minMobs;
		return (byte)randomNum;
	}
	
	public HashSet<Byte> transparentBlocks() {
		HashSet<Byte> b = new HashSet<Byte>();
		b.add((byte) 0);
		b.add((byte) 8);
		b.add((byte) 9);
		b.add((byte) 10);
		b.add((byte) 11);
		return b;
	}

}
