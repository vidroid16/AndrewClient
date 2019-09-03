package andr;

public class NameException extends RuntimeException {

    private String throwedClass;
    private String fileName;
    private String methodName;
    private int lineNum;
    private Object obj;         //Object cause andr.of exception was called

    public NameException(){
        throwedClass = this.getStackTrace()[1].getClassName();
        fileName = this.getStackTrace()[1].getFileName();
        methodName = this.getStackTrace()[1].getMethodName();
        lineNum = this.getStackTrace()[1].getLineNumber();
    }

    public NameException(Object obj){
        this();
        this.obj = obj;
    }

    public NameException(String message) {
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
