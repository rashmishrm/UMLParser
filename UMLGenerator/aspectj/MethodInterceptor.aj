import java.util.*;

public aspect MethodInterceptor {
      Stack<String> st = new Stack<String>();

	//pointcut traced() : !within(MethodInterceptor) && execution(public * *.*(..)) ;
	pointcut traced() : !within(MethodInterceptor) && execution( * *.*(..));



private String previousClass;
private String currentClass;
private int callDepth=1;
private String activate;
private String deactivate;




	before() : traced() {


        String prefix=null;
			if(callDepth==1){
        prefix="NEW_CALL:";

		}
		else{
		prefix="NESTED_CALL:";

		}
		Object o = thisJoinPoint.getThis();
        String currentClassName=null;
        if(o!=null){
        currentClassName=o.getClass().getName();
        }
        else{
      String signature=  thisJoinPointStaticPart.getSignature().toString();

      String array[]=signature.split("\\s");
         String methodName=array[1];
        System.out.println(signature);
         if(methodName.contains("."))
         currentClassName=methodName.split("\\.")[0];
       // System.out.println(currentClassName);

        }
        String sender=null;

        if(previousClass==null){
        previousClass=currentClassName;
        sender=currentClassName;
	    }
       else  if(previousClass.equals(currentClassName)){
        sender=currentClassName;


        }
        else{
        sender=previousClass;
        }


      String call=  thisJoinPointStaticPart.getSignature().toString();

      String array[]=call.split("\\s");
      String methodName=array[1];
      String returnType=array[0];

      if(methodName.contains(".")){
      methodName=methodName.split("\\.")[1];
      }
      if(st.size()>0){

      sender=st.peek();
      }

      String methodCall=methodName+":"+returnType;

//System.out.println(callDepth+"::::"+prefix+":::: "+sender+"------>" + methodCall+"---->"+currentClassName);
System.out.println("ASPECJ_TRACE: "+sender+"->"+currentClassName+":"+methodCall);

if(currentClassName !=null && !currentClassName.equals(activate)){

               // System.out.println("activate "+currentClassName);
                activate=currentClassName;

}
currentClass=currentClassName;

       st.push(currentClass);


previousClass=currentClass;
		callDepth++;





	}

	after() : traced() {
		callDepth--;
if(currentClass !=null){
if(currentClass.equals(activate)){
activate=null;
}
              //  System.out.println("deactivate "+currentClass);
                deactivate=currentClass;



}


      st.pop();

	}

	private void print(String prefix, Object message) {


		if(callDepth==1){



					System.out.print("NEW_CALL:   "+callDepth+":"+message);



		}
		else{
				System.out.print("NESTED_CALL"+callDepth+message);

		}


		//System.out.println(prefix + ": " + message);
	}
}
