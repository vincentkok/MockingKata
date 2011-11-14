package banking.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class PerformanceAspect {

	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("PROFILING");
		
	    // start stopwatch
	    Object retVal = pjp.proceed();
	    // stop stopwatch
	    return retVal;
	}	
}
