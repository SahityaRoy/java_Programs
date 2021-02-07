/*
 * The MIT License
 * Copyright © 2014-2019 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.iluwatar.leaderelection.ring;

import com.iluwatar.leaderelection.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RingMessageManager unit test.
 */
public class RingMessageManagerTest {

  @Test
  public void testSendHeartbeatMessage() {
    var instance1 = new RingInstance(null, 1, 1);
    Map<Integer, Instance> instanceMap = Map.of(1, instance1);
    var messageManager = new RingMessageManager(instanceMap);
    assertTrue(messageManager.sendHeartbeatMessage(1));
  }

  @Test
  public void testSendElectionMessage() {
    try {
      var instance1 = new RingInstance(null, 1, 1);
      var instance2 = new RingInstance(null, 1, 2);
      var instance3 = new RingInstance(null, 1, 3);
      Map<Integer, Instance> instanceMap = Map.of(1, instance1, 2, instance2, 3, instance3);
      var messageManager = new RingMessageManager(instanceMap);
      var messageContent = "2";
      messageManager.sendElectionMessage(2, messageContent);
      var ringMessage = new Message(MessageType.ELECTION, messageContent);
      var instanceClass = AbstractInstance.class;
      var messageQueueField = instanceClass.getDeclaredField("messageQueue");
      messageQueueField.setAccessible(true);
      var ringMessageSent = ((Queue<Message>) messageQueueField.get(instance3)).poll();
      assertEquals(ringMessageSent.getType(), ringMessage.getType());
      assertEquals(ringMessageSent.getContent(), ringMessage.getContent());
    } catch (NoSuchFieldException | IllegalAccessException e) {
      fail("Error to access private field.");
    }
  }

  @Test
  public void testSendLeaderMessage() {
    try {
      var instance1 = new RingInstance(null, 1, 1);
      var instance2 = new RingInstance(null, 1, 2);
      var instance3 = new RingInstance(null, 1, 3);
      Map<Integer, Instance> instanceMap = Map.of(1, instance1, 2, instance2, 3, instance3);
      var messageManager = new RingMessageManager(instanceMap);
      var messageContent = "3";
      messageManager.sendLeaderMessage(2, 3);
      var ringMessage = new Message(MessageType.LEADER, messageContent);
      var instanceClass = AbstractInstance.class;
      var messageQueueField = instanceClass.getDeclaredField("messageQueue");
      messageQueueField.setAccessible(true);
      var ringMessageSent = ((Queue<Message>) messageQueueField.get(instance3)).poll();
      assertEquals(ringMessageSent, ringMessage);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      fail("Error to access private field.");
    }
  }

  @Test
  public void testSendHeartbeatInvokeMessage() {
    try {
      var instance1 = new RingInstance(null, 1, 1);
      var instance2 = new RingInstance(null, 1, 2);
      var instance3 = new RingInstance(null, 1, 3);
      Map<Integer, Instance> instanceMap = Map.of(1, instance1, 2, instance2, 3, instance3);
      var messageManager = new RingMessageManager(instanceMap);
      messageManager.sendHeartbeatInvokeMessage(2);
      var ringMessage = new Message(MessageType.HEARTBEAT_INVOKE, "");
      var instanceClass = AbstractInstance.class;
      var messageQueueField = instanceClass.getDeclaredField("messageQueue");
      messageQueueField.setAccessible(true);
      var ringMessageSent = ((Queue<Message>) messageQueueField.get(instance3)).poll();
      assertEquals(ringMessageSent.getType(), ringMessage.getType());
      assertEquals(ringMessageSent.getContent(), ringMessage.getContent());
    } catch (NoSuchFieldException | IllegalAccessException e) {
      fail("Error to access private field.");
    }
  }

}
