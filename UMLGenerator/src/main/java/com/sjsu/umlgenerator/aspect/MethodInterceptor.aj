

public aspect MethodInterceptor {

	//pointcut traced() : !within(MethodInterceptor) && execution(public * *.*(..)) ;
	pointcut traced() : !within(MethodInterceptor) && execution(* *.*(..)) ;

	before() : traced() {
		print("Executing", thisJoinPoint);
	}

	

	private void print(String prefix, Object message) {

		System.out.println(prefix + ": " + message);
	}
}