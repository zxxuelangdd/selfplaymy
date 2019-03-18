import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: selfplay
 * @description: 随机测试一些, 测完就删
 * @author: zx
 * @create: 2018-07-26 14:10
 **/
public class JustTest {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(JustTest.class);
        int i = 0;
        i = i + 99;
        System.out.println(i);
        logger.info("adfasd");

        String t = "  s  ad f a   ";
        System.out.println("===========" + t);
        String trim = StringUtils.trim(t);

        String trimToEmpty = StringUtils.trimToEmpty(t);
        System.out.println("trimToEmpty====" + trimToEmpty);

        String trimToNull = StringUtils.trimToNull(t);
        System.out.println("trimToNull==========" + trimToNull);

        System.out.println("trim==========" + trim);

        String a = null;
        System.out.println("aaaa=======" + StringUtils.isEmpty(a));
        System.out.println("aaaa=======" + StringUtils.isBlank(a));


        String name = "111";
        boolean blank = StringUtils.isBlank(name);
        boolean numeric = StringUtils.isNumeric(name);
        logger.info("blank:" + blank);
        logger.info("numeric:" + StringUtils.isNumeric("34234"));
        logger.info("是不是全是字母:" + StringUtils.isAlpha("ddasfdasfas是"));


    }
}
