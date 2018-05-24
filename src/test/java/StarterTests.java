import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.sap.chatbot.ChatbotStarterService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChatbotStarterService.class)
@ActiveProfiles("test")
public class StarterTests {

  @Test
  public void test() {
    Assert.assertTrue(true);
  }

}
