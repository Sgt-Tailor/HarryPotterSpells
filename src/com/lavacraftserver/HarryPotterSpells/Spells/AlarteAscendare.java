package com.lavacraftserver.HarryPotterSpells.Spells;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.lavacraftserver.HarryPotterSpells.HPS;
import com.lavacraftserver.HarryPotterSpells.Spells.Spell.spell;
import com.lavacraftserver.HarryPotterSpells.Utils.Targeter;

@spell (
		name="AlarteAscendare",
		description="Propels the targeted mob upward",
		range=30,
		goThroughWalls=false
)
public class AlarteAscendare extends Spell {
	
	public void cast(Player p) {
		if(Targeter.getTarget(p, this.getRange(), this.canBeCastThroughWalls()) instanceof LivingEntity) {
			LivingEntity le = (LivingEntity) Targeter.getTarget(p, this.getRange(), this.canBeCastThroughWalls());
			le.setVelocity(new Vector(0,1,0));
			if(!HPS.MiscListeners.alarteascendare.contains(le.getEntityId())){
				HPS.MiscListeners.alarteascendare.add(le.getEntityId());
			}
		}
	}
}
