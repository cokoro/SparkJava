<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Sign In">
    <#if message??>
        <div class="alert alert-success col-md-9">
            ${message}
        </div>
    </#if>
    <#if error??>
        <div class="alert alert-danger">
            <strong>Error:</strong> ${error}
        </div>
    </#if>

<!--
<form method='post' action='getPredictions' enctype='multipart/form-data'>
<div class="input-group">
  <div class="custom-file">
    <input type="file" name='uploaded_file' class="custom-file-input" id="inputGroupFile04">
    <label class="custom-file-label" for="inputGroupFile04">Choose file</label>
  </div>
  <div class="input-group-append">
    <button class="btn btn-outline-secondary" type="button">Button</button>
  </div>
</div>

<form method='post' action='getPredictions' enctype='multipart/form-data'>
                   <input type='file'  class="col-sm-12" name='uploaded_file'>
                    <button class="btn btn-default col-md-offset-6">Upload picture</button>
               </form>
-->
    <form class="form-horizontal" action="getPredictions" role="form" method="post">

        <div class="col-md-9 form-group">
            <label for="username" class="col-md-2 control-label">Choose File: </label>
            <div class="col-md-7">
                <input type="file" name='uploaded_file' id="username" placeholder="Username" value="Please choose a file" />
            </div>
        </div>
<!--
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label"> </label>
            <div class="col-sm-7">
                <img src="${pic_address}">
            </div>
        </div>
-->
        <div class="form-group col-md-9">
            <div class="col-md-offset-7">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>

     </form>
</@layout.masterTemplate>
