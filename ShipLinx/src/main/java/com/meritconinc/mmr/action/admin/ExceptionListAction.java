package com.meritconinc.mmr.action.admin;

import java.util.List;

import com.meritconinc.mmr.action.common.DataGridAction;
import com.meritconinc.mmr.model.admin.ExceptionSearchCriteria;
import com.meritconinc.mmr.model.common.ExceptionInfoVO;
import com.meritconinc.mmr.model.datagrid.DataGrid;
import com.meritconinc.mmr.service.ExceptionInfoService;

public class ExceptionListAction extends DataGridAction {
  private static final long serialVersionUID = -1032610026888122174L;

  private ExceptionInfoService service;

  private ExceptionSearchCriteria criteria;

  private boolean resetCurrPage = false;
  private int noOfRowsPerPage;

  public void setDataGridPages() {
    this.setNoOfRowsPerPage(10);
    setDataGrid(new DataGrid(noOfRowsPerPage));
  }

  public ExceptionSearchCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(ExceptionSearchCriteria criteria) {
    this.criteria = criteria;
  }

  public ExceptionListAction(ExceptionInfoService service) {
    this.service = service;
    if (criteria == null) {
      criteria = new ExceptionSearchCriteria();
    }
  }

  public String execute() throws Exception {
    return SUCCESS;
  }

  public ExceptionInfoService getService() {
    return service;
  }

  public String list() throws Exception {
    getDataGrid().resetCurrentPage();

    int rowCount = service.findDataRowsCount(criteria);
    if (rowCount > 0) {
      setDataGrid(new DataGrid(rowCount, noOfRowsPerPage));
      getDataGrid().setDataRows(
          service.findExceptions(criteria, getDataGrid().getCurPageRowStartIndex(), getDataGrid()
              .getCurPageRowEndIndex()));
    } else {
      addActionError(getText("error.no.exception.found"));
    }
    resetCurrPage = false;
    return SUCCESS;
  }

  public String back() throws Exception {
    int rowCount = service.findDataRowsCount(criteria);
    if (rowCount > 0) {
      setDataGrid(new DataGrid(rowCount, noOfRowsPerPage));
      getDataGrid().setDataRows(refreshDataRows());
    }
    return SUCCESS;
  }

  public List<ExceptionInfoVO> refreshDataRows() throws Exception {
    List<ExceptionInfoVO> exceptionList = service.findExceptions(criteria, getDataGrid()
        .getCurPageRowStartIndex(), getDataGrid().getCurPageRowEndIndex());
    if (exceptionList.size() <= 0) {
      addActionError(getText("error.no.exception.found"));
    }
    return exceptionList;
  }

  public int getNoOfRowsPerPage() {
    return noOfRowsPerPage;
  }

  public void setNoOfRowsPerPage(int noOfRowsPerPage) {
    this.noOfRowsPerPage = noOfRowsPerPage;
  }

  public boolean isResetCurrPage() {
    return resetCurrPage;
  }

  public void setResetCurrPage(boolean resetCurrPage) {
    this.resetCurrPage = resetCurrPage;
  }

  public String goToSearch() throws Exception {
    if (resetCurrPage) {
      getDataGrid().resetCurrentPage();
    }
    return INPUT;
  }
}
