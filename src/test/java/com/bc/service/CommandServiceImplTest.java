package com.bc.service;

import com.bc.BaseApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
public class CommandServiceImplTest {

    @Autowired
    private CommandService commandService;

    @Test
    public void testCmd() {
        String s = commandService.executeCmd("touch ./x.txt");
        System.out.println(s);
    }
}
