package net.jordaria.event;

public class LanguageChanged extends Event{
	private static final HandlerList handlers = new HandlerList();

	String lang;

	public LanguageChanged(String newLanguage){
		this.lang = newLanguage;
	}

	public String getNewLang(){
		if (this.lang == null){
			return "";
		}
		else{
			return this.lang;
		}
	}
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}


	public static HandlerList getHandlerList() {
		return handlers;
	}
}
