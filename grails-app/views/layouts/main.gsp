<!DOCTYPE html>
<html lang="en">
<head>
<title>LMA &raquo; <g:layoutTitle default="Lepidoptera of the Mid-Atlantic" /></title>
<link rel="stylesheet" href="${resource(dir:'css',file:'lepidoptera.css')}" />
<link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
<g:layoutHead />
<g:javascript library="jquery" plugin="jquery" />
<g:javascript library="pageload"/>
</head>
<body>
   <div id="bg">
      <div id="attribution">&nbsp;</div>
   </div>
   <div id="content_pane" class="bg_trans">
      <div id="scroll_pane">
         <g:layoutBody />
      </div>
   </div>
   <div id="tool_pane" class="bg_trans">
      <div id="home" class="tool_item tool_item_link">
         <img alt="Home" class="tool" title="Home" align="absmiddle" src="images/home.png"></span>Home</span>
      </div>
      <div id="family" class="tool_item tool_item_link">
         <img alt="Families" class="tool" title="Families" align="absmiddle" src="images/butterfly.png"></span>Families</span>
      </div>
      <div id="butterfly" class="tool_item tool_item_link">
         <img alt="Butterflies" class="tool" title="Butterflies" align="absmiddle" src="images/butterfly.png"></span>Butterflies</span>
      </div>
      <div id="login" class="tool_item no_auth">
         <img alt="Login" class="tool" title="Login" align="absmiddle" src="images/login.png" /><span>Login</span>
      </div>
      <div id="logout" style="display: none" class="tool_item auth_only">
         <img alt="Logout" class="tool" title="Logout" align="absmiddle" src="images/logout.png" /><span>Logout</span>
      </div>
   </div>
</body>
</html>