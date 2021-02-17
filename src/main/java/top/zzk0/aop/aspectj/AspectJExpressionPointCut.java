package top.zzk0.aop.aspectj;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class AspectJExpressionPointCut implements PointCut, ClassFilter, MethodMatcher {

    private String expression;
    private PointcutParser parser;
    private PointcutExpression pointCutExpression;
    private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<>();

    static {
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
    }

    public AspectJExpressionPointCut() {
        this(DEFAULT_SUPPORTED_PRIMITIVES);
    }

    public AspectJExpressionPointCut(Set<PointcutPrimitive> supportedPrimitive) {
        parser = PointcutParser.
                getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPrimitive);
    }

    public void checkReadyToMatch() {
        if (pointCutExpression == null) {
            this.pointCutExpression = parser.parsePointcutExpression(expression);
        }
    }

    public void setExpression(String expression) {
        this.expression = expression;
        this.pointCutExpression = null;
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

    @Override
    public boolean matches(Class targetClass) {
        checkReadyToMatch();
        return pointCutExpression.couldMatchJoinPointsInType(targetClass);
    }

    @Override
    public boolean matches(Method method, Class targetClass) {
        checkReadyToMatch();
        ShadowMatch shadowMatch = pointCutExpression.matchesMethodExecution(method);
        if (shadowMatch.alwaysMatches()) {
            return true;
        }
        else if (shadowMatch.neverMatches()) {
            return false;
        }
        return false;
    }
}
