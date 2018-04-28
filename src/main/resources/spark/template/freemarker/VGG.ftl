<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="prediction">
    <#if message??>
        <div class="alert alert-success col-md-9">
            ${message}
        </div>
    </#if>

    <form class="form-horizontal" enctype='multipart/form-data' action="getPredictions" role="form" method="post">
        <div class="col-md-9 form-group">
            <label for="username" class="col-md-2 control-label">Choose File: </label>
            <div class="col-md-7">
                <input type="file" name='uploaded_file' id="username" value="Please choose a file" />
            </div>
        </div>
        <div class="form-group col-md-9">
            <div class="col-md-offset-7">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
     </form>
</@layout.masterTemplate>
