package fr.slem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AppTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public BusinessExceptionRule businessExceptionRule = new BusinessExceptionRule();

    @Test
    public void willPass() throws BusinessException {

        expectedException.expect(BusinessException.class);
        expectedException.expectMessage("user.not.found");
        businessExceptionRule.setParameters("Batman");

        throw new BusinessException("user.not.found", "Batman");
    }

    @Test
    public void willPassWithNoParameters() throws BusinessException {
        expectedException.expect(BusinessException.class);
        expectedException.expectMessage("user.not.found");
        throw new BusinessException("user.not.found");
    }

    @Test
    public void willFail() throws BusinessException {

        expectedException.expect(BusinessException.class);
        expectedException.expectMessage("user.not.found");
        businessExceptionRule.setParameters("Batman", "Robin");
        throw new BusinessException("user.not.found", "Batman");
    }
}
