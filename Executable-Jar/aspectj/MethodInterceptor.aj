import java.util.*;

public aspect MethodInterceptor {
      Stack<String> st = new Stack<String>();
	pointcut traced() : !within(MethodInterceptor) && !cflow(initialization(*.new())) && execution( * *.*(..));



	before() : traced(){
		Object o = thisJoinPoint.getThis();
        String currentClassName=null;
        if(o!=null){
        currentClassName=o.getClass().getName();
        }
        else{
      	String signature=  thisJoinPointStaticPart.getSignature().toString();

      	String array[]=signature.split("\\s");
         String methodName=array[1];
         if(methodName.contains("."))
         currentClassName=methodName.split("\\.")[0];
        }
        String sender=null;


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
      else{
      sender=currentClassName;
      }

     	String methodCall=methodName+":"+returnType;
		System.out.println("ASPECJ_TRACE: "+sender+"->"+currentClassName+":"+methodCall);

        st.push(currentClassName);

	}


	after() : traced() {

    if(st.size()>0)
      st.pop();
	}

}
