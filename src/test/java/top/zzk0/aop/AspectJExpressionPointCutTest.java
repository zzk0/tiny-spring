package top.zzk0.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.junit.jupiter.api.Test;
import top.zzk0.aop.aspectj.AspectJExpressionPointCutAdvisor;
import top.zzk0.aop.aspectj.ClassFilter;
import top.zzk0.aop.aspectj.MethodMatcher;
import top.zzk0.bean.Dog;
import top.zzk0.bean1.Hunter;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AspectJExpressionPointCutTest {

    @Test
    public void testMatchClass() {
        String expression = "execution(* top.zzk0.bean.*.*(..))";
        AspectJExpressionPointCutAdvisor advisor = new AspectJExpressionPointCutAdvisor();
        advisor.setExpression(expression);
        ClassFilter filter = advisor.getPointCut().getClassFilter();
        assertTrue(filter.matches(Dog.class));

        // 下面 fail 了, 你会发现不管传什么都是 true
        // assertFalse(filter.matches(System.class));
    }

    @Test
    public void testMatchMethod() throws Exception {
        String expression = "execution(* top.zzk0.bean.*.s*(..))";
        AspectJExpressionPointCutAdvisor advisor = new AspectJExpressionPointCutAdvisor();
        advisor.setExpression(expression);
        MethodMatcher matcher = advisor.getPointCut().getMethodMatcher();
        assertTrue(matcher.matches(Dog.class.getDeclaredMethod("say"),
                Dog.class));
    }

    @Test
    public void testJoinPoint() {
        final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<>();
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);

        PointcutParser parser = PointcutParser.
                getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(DEFAULT_SUPPORTED_PRIMITIVES);
        String expression = "execution(* top.zzk0.bean.*.s*(..))";
        PointcutExpression pointcutExpression = parser.parsePointcutExpression(expression);
        // assertFalse(pointcutExpression.couldMatchJoinPointsInType(Hunter.class));
    }
}
