package Model;

public interface Control {
	void setSelectedControl(Boolean selected);
	boolean isSelectedControl();
	TypeControl returnType();
	boolean inArea(int x,int y);
}
