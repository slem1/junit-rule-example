package fr.slem;

import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @author slemoine
 */
public class BusinessExceptionRule implements TestRule {

    private Object[] parameters;

    public void setParameters(Object... parameters) {
        this.parameters = parameters;
    }

    public Statement apply(Statement base, Description description) {
        this.parameters = new Object[0];
        return new BusinessExceptionStatement(base);
    }

    class BusinessExceptionStatement extends Statement {

        private final Statement base;

        public BusinessExceptionStatement(Statement base) {
            this.base = base;
        }

        @Override
        public void evaluate() throws Throwable {

            try {
                base.evaluate();
            } catch (Exception e) {
                if (e instanceof BusinessException) {
                    Object[] parameters = ((BusinessException) e).getParameters();
                    Assert.assertArrayEquals("Unexpected parameters for " + BusinessException.class,
                            BusinessExceptionRule.this.parameters, parameters);
                }

                throw e;

            }
        }
    }
}
