package com.bupt.service.search.node;
/**
 * 创建时间：2017-08-31
 * 节点说明：存储一个文件里面定义的变量的变量名，和文件内位置
 * */
public class VariableNode {
	/** The line number of the first character of this Token. */
	public int beginLine;
	/** The column number of the first character of this Token. */
	public int beginColumn;
	/** The line number of the last character of this Token. */
	//public int endLine;
	/** The column number of the last character of this Token. */
	public int endColumn;
	/** The variable name */
	public String image;
	public VariableNode(int stline, int stcol, int encol, String image){
		this.beginLine = stline;
		//this.endLine = enline;
		this.beginColumn = stcol;
		this.endColumn = encol;
		this.image = image;
	}
	public int getBeginLine() {
		return beginLine;
	}

	public void setBeginLine(int beginLine) {
		this.beginLine = beginLine;
	}

	public int getBeginColumn() {
		return beginColumn;
	}

	public void setBeginColumn(int beginColumn) {
		this.beginColumn = beginColumn;
	}

	public int getEndColumn() {
		return endColumn;
	}

	public void setEndColumn(int endColumn) {
		this.endColumn = endColumn;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	public String toString(){
		return (image+", Line:"+beginLine+", Column:["+beginColumn+","+endColumn+"]"+"");
	}
}
