<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>
<div class ="row">
    <div class = "col-lg-12">
        <h1 class="page-header">
            Board Read
        </h1>
    </div>
</div>

<div class= "row">
    <div class = "col-lg-12">
        <div class="panel panel-default">
            <div class ="panel-heading">Board Modify Page</div>
            <!-- /.panel-heading -->
            <form role="form" action="/board/modify" method="post">
                <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
                <input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
                <input type="hidden" name="type" value='<c:out value="${cri.type}"/>'>
                <input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>
                <div class="form-group">
                    <label>Bno</label>
                    <input class ="form-control" name="bno" value='<c:out value="${board.bno}"/>' readonly ="readonly">
                </div>

                <div class="form-group">
                    <label>Title</label>
                    <input class ="form-control" name="title" value='<c:out value="${board.title}"/>'>
                </div>

                <div class="form-group">
                    <label>Text area</label>
                    <textarea class="form-control" rows="3" name="content"><c:out value="${board.content}"/></textarea>
                </div>

                <div class="form-group">
                    <label>Writer</label>
                    <input class ="form-control" name="writer" value='<c:out value="${board.writer}"/>' readonly ="readonly">
                </div>

                <button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
                <button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
                <button type="submit" data-oper='list' class="btn btn-info">List</button>
            </form>
    <%--        end form--%>
        </div>
        <%--    end panel--%>
    </div>
</div>
<%--end row--%>
<%@include file="../includes/footer.jsp" %>

<script type="text/javascript">
    $(document).ready(function (){
       let formObj = $("form");

       $('button').on("click", function (e){
           e.preventDefault();

           let operation = $(this).data("oper");

           console.log(operation);

           if(operation === 'remove'){
               formObj.attr("action", "/board/remove");
           }else if(operation === 'list'){
               formObj.attr("action", "/board/list").attr("method","get");

               let pageNumTag = $("input[name='pageNum']").clone();
               let amountTag = $("input[name='amount']").clone();
               let keywordTag = $("input[name='keyword']").clone();
               let typeTag = $("input[name='type']").clone();

               formObj.empty();
               formObj.append(pageNumTag);
               formObj.append(amountTag);
               formObj.append(keywordTag);
               formObj.append(typeTag);
           }
           formObj.submit();
       });
    });
</script>