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

package com.iluwatar.commander.shippingservice;

import com.iluwatar.commander.Database;
import com.iluwatar.commander.exceptions.DatabaseUnavailableException;
import com.iluwatar.commander.shippingservice.ShippingService.ShippingRequest;
import java.util.Hashtable;

/**
 * ShippingDatabase is where the ShippingRequest objects are added.
 */

public class ShippingDatabase extends Database<ShippingRequest> {

  private final Hashtable<String, ShippingRequest> data;

  public ShippingDatabase() {
    this.data = new Hashtable<>();
  }

  @Override
  public ShippingRequest add(ShippingRequest r) {
    return data.put(r.transactionId, r);
  }

  public ShippingRequest get(String trasnactionId) {
    return data.get(trasnactionId);
  }

}
