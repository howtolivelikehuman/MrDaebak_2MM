package Service;

public class ActionForward { // 다음 동작
	private boolean isRedirect; // true : redirect 할 것이다. false : forward 할 것이다. 
	private String nextPath; 	// 다음 경로
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getNextPath() {
		return nextPath;
	}
	public void setNextPath(String nextPath) {
		this.nextPath = nextPath;
	}
}
