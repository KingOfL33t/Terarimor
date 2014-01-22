package net.jordaria.text;

public class ColoredText {
	String text;
	Color color;
	public ColoredText(String text){
		this.text = text;
		this.color = Color.BLACK;
	}
	public ColoredText(String text, Color color){
		this.text = text;
		this.color = color;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
}
