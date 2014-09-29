package taskDo;

/*
 * @author Paing Zin Oo(Jack)
 */
public class Controller {
	//NEED THE RETURN TYPE FROM EXECUTOR WHETHER SUCCEEFUL OR NOT 
	String userCommand;
	Executor executor;
	UiViewModifier uiViewModifier;
	
	public Controller(){
		Parser.parserInit();
		executor = new Executor();
		uiViewModifier = new UiViewModifier();
	}

	public String getUserCommand() {
		setUserCommand(uiViewModifier.getCommand());
		return uiViewModifier.getCommand();
	}

	public void setUserCommand(String userCommand) {
		this.userCommand = userCommand;
	}
	
	public void parseToParser(){
		//Parser will parse boolean 
		if(getUserCommand()!=null){
			if(Parser.parseString(userCommand)){
				System.out.println("Parse String reached here");
				executor.execute();
			}
			uiViewModifier.updateUi();
		}
		
	}
	
	
}
