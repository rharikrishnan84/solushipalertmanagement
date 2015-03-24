<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<html>
<body>
									<div class="fields">
										<label><mmr:message messageId="label.menu.firstLevel"/> </label>
										<div class="controls"><span>:</span>
										<s:select   name="menuScreenVO.firstLevel"  listKey="id" listValue="name" cssClass="changerole"
										headerKey="-1"  headerValue="--First Level--" list="firstMenuVo" theme="simple"  id="idFirstLevel" />
									</div>
									</div>
									</body>
									</html>