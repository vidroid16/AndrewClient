package andr;

public class InvalidDateExeption extends Exception{

    private String throwedClass;
    private String fileName;
    private String methodName;
    private int lineNum;
    private Object obj;         //Object cause andr.of exception was called

    public InvalidDateExeption(){
        throwedClass = this.getStackTrace()[1].getClassName();
        fileName = this.getStackTrace()[1].getFileName();
        methodName = this.getStackTrace()[1].getMethodName();
        lineNum = this.getStackTrace()[1].getLineNumber();
    }

    public InvalidDateExeption(Object obj){
        this.obj = obj;
    }

    public InvalidDateExeption(String message) {
        super(message);
        throwedClass = this.getStackTrace()[1].getClassName();
        fileName = this.getStackTrace()[1].getFileName();
        methodName = this.getStackTrace()[1].getMethodName();
        lineNum = this.getStackTrace()[1].getLineNumber();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public String getThrowedClass() {
        return throwedClass;
    }

    public String getFileName() {
        return fileName;
    }

    public String getMethodName() {
        return methodName;
    }

    public int getLineNum() {
        return lineNum;
    }

    public Object getObj() {
        return obj;
    }
}

