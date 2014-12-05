package commands
import org.crsh.cli.Argument
import org.crsh.cli.Command
import org.crsh.cli.Man
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
/**
 *
 * @Author Martin Tosovsky
 *
 */
class hello {

    @Usage("Hello <name>")
    @Man("Output will beHello <name> in Spring Boot <version>")
    @Command
    def main(InvocationContext context,  @Argument(name = "name") String name) {
        return "Hello $name in Spring Boot " + context.attributes.get("spring.boot.version");
    }
}
