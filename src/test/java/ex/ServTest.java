package ex;

import com.google.zxing.WriterException;
import org.example.Serv;
import org.example.TreeNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ServTest {


    @Test
    void testExample() throws IOException, WriterException {
        TreeNode p = new TreeNode(2, new TreeNode(1), null);
        TreeNode q = new TreeNode(2, null, new TreeNode(1));
        assertThat(Serv.isSameTree(p, q)).isTrue();
    }
    @Test
    void t2() {
        assertThat(Serv.intToRoman(8)).isEqualTo("VIII");
    }

}
