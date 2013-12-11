package net.jordaria.entity.genetics;

import net.jordaria.entity.genetics.GeneEnums.genePosition;
import net.jordaria.entity.genetics.GeneEnums.health;
import net.jordaria.entity.genetics.GeneEnums.moveSpeed;
import net.jordaria.entity.genetics.GeneEnums.size;

public class DNA {
	/*
	 * This is the list of genes.
	 * The genes will be set via setters and the set retrieved via a getter
	 */
	private byte[] dna = new byte[32];
	
	public DNA(){

	}
	
	private byte[] getNormalGenes(){
		byte[] geneArray = new byte[dna.length];
		geneArray[genePosition.HEALTH.pos] = health.NORMAL.geneData;
		geneArray[genePosition.MOVE_SPEED.pos] = moveSpeed.NORMAL.geneData;
		geneArray[genePosition.SIZE.pos] = size.NORMAL.geneData;
		return geneArray;
	}
	
}
