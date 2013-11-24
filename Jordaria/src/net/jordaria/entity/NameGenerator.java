package net.jordaria.entity;

public class NameGenerator {
	public NameGenerator(){
	}
	private String[] prefix = new String[]{"Fa","Ae","Ja","Du","Mo","Ne","Ro","Te","Ga","Va"};
	private String[] middle = new String[]{"dor","sorn","par","bard","jur","nen","rist","thek","zar","wren"};
	private String[] suffix = new String[]{"and","marth","eth","orn","son","ath","lea","wrath","don","ria"};
	public String getRandomName(){
		String name = "Tim?";
		String p = prefix[(int)(Math.random()*prefix.length)];
		String m = middle[(int)(Math.random()*middle.length)];
		String s = suffix[(int)(Math.random()*suffix.length)];
		name = p.concat(m).concat(s);
		return name;
	}
}
