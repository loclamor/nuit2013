package nuitinfo2013



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ExchangeController)
class ExchangeControllerTests {

    void testGetExchange() {
        User.getAll()[0]
        Exchange ret = getExchange(User.getAll()[0])
        assert (ret!=null)
    }
}