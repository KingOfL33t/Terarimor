package net.jordaria.math;

import net.jordaria.entity.Location;

public class GraphicsMath {
	
	public Location isoTo2D(Location pt){
		Location tempPt = new Location(0, 0,0);
		tempPt.posX = (2 * pt.posY + pt.posX) / 2;
		tempPt.posY = (2 * pt.posY - pt.posX) / 2;
		return(tempPt);
	}

	public Location twoDToIso(Location pt){
		Location tempPt = new Location(0,0,0);
		tempPt.posX = pt.posX - pt.posY;
		tempPt.posY = (pt.posX + pt.posY) / 2;
		return(tempPt);
	}
}
