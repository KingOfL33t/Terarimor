package net.jordaria.item;

import net.jordaria.text.Color;
import net.jordaria.text.ColoredText;

public class Lore {
	public ColoredText line1;
	public ColoredText line2;
	public ColoredText line3;
	
	public Lore(){
		this.line1 = new ColoredText("");
		this.line2 = new ColoredText("");
		this.line3 = new ColoredText("");
	}
	public Lore(String line1){
		this.line1 = new ColoredText(line1);
		this.line2 = new ColoredText("");
		this.line3 = new ColoredText("");
	}
	public Lore(String line1, Color line1color){
		this.line1 = new ColoredText(line1, line1color);
		this.line2 = new ColoredText("");
		this.line3 = new ColoredText("");
	}
	public Lore(String line1, String line2){
		this.line1 = new ColoredText(line1);
		this.line2 = new ColoredText(line2);
		this.line3 = new ColoredText("");
	}
	public Lore(String line1, Color line1color, String line2, Color line2color){
		this.line1 = new ColoredText(line1, line1color);
		this.line2 = new ColoredText(line2, line2color);
		this.line3 = new ColoredText("");
	}
	public Lore(String line1, String line2, String line3){
		this.line1 = new ColoredText(line1);
		this.line2 = new ColoredText(line2);
		this.line3 = new ColoredText(line3);
	}
	public Lore(String line1, Color line1color, String line2, Color line2color, String line3, Color line3color){
		this.line1 = new ColoredText(line1, line1color);
		this.line2 = new ColoredText(line2, line2color);
		this.line3 = new ColoredText(line3, line3color);
	}
	public ColoredText getLine1(){
		return line1;
	}
	public ColoredText getLine2(){
		return line2;
	}
	public ColoredText getLine3(){
		return line3;
	}
	public ColoredText getLine(int index){
		switch(index){
		case 1 : return getLine1();
		case 2 : return getLine2();
		case 3 : return getLine3();
		default : return new ColoredText("");
		}
	}
	public int getLinesUsed(){
		int count = 0;
		if (!this.line1.getText().isEmpty()){
			count++;
		}
		if (!this.line2.getText().isEmpty()){
			count++;
		}
		if (!this.line3.getText().isEmpty()){
			count++;
		}
		return count;
	}
}
