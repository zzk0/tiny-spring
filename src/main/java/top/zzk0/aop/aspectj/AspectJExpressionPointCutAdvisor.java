package top.zzk0.aop.aspectj;

import org.aopalliance.aop.Advice;

public class AspectJExpressionPointCutAdvisor implements PointCutAdvisor {

    private AspectJExpressionPointCut pointCut = new AspectJExpressionPointCut();
    private Advice advice;

    public void setPointCut(AspectJExpressionPointCut pointCut) {
        this.pointCut = pointCut;
    }

    @Override
    public PointCut getPointCut() {
        return pointCut;
    }

    public void setExpression(String expression) {
        pointCut.setExpression(expression);
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
