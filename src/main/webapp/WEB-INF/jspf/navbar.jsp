<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- Shared top navbar. Include with:
       <c:set var="activePage" value="Home|Pricing|Material|Tracker|Account" scope="request" />
       <jsp:include page="/WEB-INF/jspf/navbar.jsp" />
     Optionally also set accountVariant to "plain" (auth.jsp: no session yet, plain
     link to auth.jsp) or "toggle" (index.jsp: reachable both logged-in and logged-out,
     shows/hides via JS). Default (unset) is the logged-in dropdown with Log Out. --%>
<section class="bg-white">
	<div class="container">
   		<div class="d-flex flex-wrap justify-content-center py-3 mb-4" id="headbar">
	      <p class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
	      <img src="icon.png" class="img-fluid d-sm-block d-none" style="width: 95px; margin-left: 90px">
	      </p>

	      <ul class="nav nav-pills">
	        <li class="nav-item mr-5"><a href="HomeService" class="nav-link" id="Home" style="${activePage == 'Home' ? 'color: #4D73FF' : ''}">Home</a></li>
	        <li class="nav-item mr-5"><a href="PricingService" class="nav-link" id="Pricing" style="${activePage == 'Pricing' ? 'color: #4D73FF' : ''}">Pricing</a></li>
	        <li class="nav-item mr-5"><a href="MatService" class="nav-link" id="Material" style="${activePage == 'Material' ? 'color: #4D73FF' : ''}">Material</a></li>
	        <li class="nav-item mr-5"><a href="TrackerService" class="nav-link" id="Tracker" style="${activePage == 'Tracker' ? 'color: #4D73FF' : ''}">Tracker</a></li>
			<li class="nav-item mr-5"><a href="BundlePatternService" class="nav-link" id="Bundle" style="${activePage == 'Bundle' ? 'color: #4D73FF' : ''}">Bundle</a></li>
			  <c:choose>
	          <c:when test="${accountVariant == 'plain'}">
	            <li class="nav-item mr-5"><a href="auth.jsp" class="nav-link" id="Account" style="${activePage == 'Account' ? 'color: #4D73FF' : ''}">Account</a></li>
	          </c:when>
	          <c:when test="${accountVariant == 'toggle'}">
	            <li class="nav-item mr-5" id="goToLogin"><a href="auth.jsp" class="nav-link" id="Account">Account</a></li>
	            <li class="nav-item dropdown mr-5" id="AccountSetting" style="display: none">
				   <a class="nav-link  dropdown-toggle" href="#" data-bs-toggle="dropdown">Account</a>
				    <ul class="dropdown-menu">
					  <li><a class="dropdown-item" href="LogoutService">Log Out</a></li>
				    </ul>
				</li>
	          </c:when>
	          <c:otherwise>
	           	<li class="nav-item dropdown mr-5">
				   <a class="nav-link  dropdown-toggle" href="#" data-bs-toggle="dropdown">Account</a>
				    <ul class="dropdown-menu">
					  <li><a class="dropdown-item" href="LogoutService">Log Out</a></li>
				    </ul>
				</li>
	          </c:otherwise>
	        </c:choose>
	      </ul>
	    </div>
    </div>
</section>
