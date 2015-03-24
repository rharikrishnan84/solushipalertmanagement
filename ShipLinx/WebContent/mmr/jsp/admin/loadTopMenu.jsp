<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<html>
<body>
<div class="fields">
										<label><mmr:message messageId="label.menu.topLevel"/> </label>
										<div class="controls"><span>:</span>
										<s:select   name="menuScreenVO.topLevel"  listKey="id" listValue="name" cssClass="changerole"
										headerKey="-1"  headerValue="--Top Level--" list="topMenuVo" theme="simple"  id="idTopLevel" onchange="javascript:showSecondMenuLevel();"/>
									</div>
									</div>
								 
									
									</body>
									</html>