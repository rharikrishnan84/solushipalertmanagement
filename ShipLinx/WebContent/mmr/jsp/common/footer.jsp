						</div>
					</div>

				</div>
			</div>
		</div>
		
		<%@ taglib prefix="mmr" uri="/mmr-tags" %>
		<div class="footer">
			<div class="footer_body">
				<div class="footer_inner">
					<!-- <p>&copy;2014 Integrated Carriers. All Rights Reserved</p>
					<p>Powered by ICU Leading the way. Designed by Integrated Carriers.</p>
				</div> -->
				<!-- <p>&copy;2014 Integrated Carriers. All Rights Reserved</p>
					<p>Powered by ICU Leading the way. Designed by Integrated Carriers.</p> -->
						
						<%if(com.opensymphony.xwork2.ActionContext.getContext().getSession().get("footer_1")!=null){ %>
                      <p> 
                       <%out.println(com.opensymphony.xwork2.ActionContext.getContext().getSession().get("footer_1"));%>		
	                   </p>
	                   <%} else{%>
	                  <!--  <p>&copy;2014 Integrated Carriers. All Rights Reserved</p> -->
	                   <p>&copy;<mmr:message messageId="message.footer.integratedcarriers"/></p>
	                   <%} %>
						
						<%if(com.opensymphony.xwork2.ActionContext.getContext().getSession().get("footer_2")!=null){ %>
		                <p>
			    		<%out.println(com.opensymphony.xwork2.ActionContext.getContext().getSession().get("footer_2"));%>
		               </p>
	                   <%} else{%>
					   <!-- <p>Powered by ICU Leading the way. Designed by Integrated Carriers.</p> -->
					   <p><mmr:message messageId="message.footer.icu"/></p>   
	                   <%} %>
						
			 				</div>
			</div>
		</div>
	</div>
</body>
</html>