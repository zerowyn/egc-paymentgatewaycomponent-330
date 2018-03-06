/**
 * Copyright 2017-2025 Evergrande Group.
 */
package api;

import com.eg.egsc.framework.client.core.ClientConfig;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.web.SpringBootMockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.support.GenericWebApplicationContext;

/**
 * 测试单元的统一配置
 *
 * @author wanghongben
 * @since 2018年1月23日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ClientConfig.class})
public abstract class AbstractUnitTestSupport {

    protected final Logger logger = Logger.getLogger(this.getClass());

    protected MockMvc mockMvc;

    @Autowired
    private GenericWebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        logger.debug("##################################################");
        logger.debug(wac.getApplicationName());
        SpringBootMockServletContext servletContext =
                (SpringBootMockServletContext) wac.getServletContext();

        // 设置context-path
        servletContext.setContextPath("/demo");
    }

}
