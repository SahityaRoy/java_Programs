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

package com.iluwatar.commander.employeehandle;

import com.iluwatar.commander.Database;
import com.iluwatar.commander.Order;
import com.iluwatar.commander.exceptions.DatabaseUnavailableException;
import java.util.Hashtable;

/**
 * The Employee Database is where orders which have encountered some issue(s) are added.
 */

public class EmployeeDatabase extends Database<Order> {
  private final Hashtable<String, Order> data;

  public EmployeeDatabase() {
    this.data = new Hashtable<>();
  }

  @Override
  public Order add(Order o) throws DatabaseUnavailableException {
    return data.put(o.id, o);
  }

  @Override
  public Order get(String orderId) throws DatabaseUnavailableException {
    return data.get(orderId);
  }
}
