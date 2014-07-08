package com.meritconinc.mmr.exception;

import com.meritconinc.shiplinx.model.CreditUsageReport;

public class CreditOverrunException extends Exception {

  private CreditUsageReport cur;

  public CreditOverrunException(CreditUsageReport cur) {
    super();
    this.cur = cur;
  }

  public CreditOverrunException(String msg, CreditUsageReport cur) {
    super(msg);
    this.cur = cur;
  }

  public CreditUsageReport getCur() {
    return cur;
  }

  public void setCur(CreditUsageReport cur) {
    this.cur = cur;
  }

}
