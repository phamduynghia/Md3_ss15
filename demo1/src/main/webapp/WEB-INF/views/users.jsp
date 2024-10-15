<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<table class="table table-striped table-inverse table-responsive">
    <thead class="thead-inverse">
    <tr>
        <th>STT</th>
        <th>NAME</th>
        <th>EMAIL</th>
        <th>STATUS</th>
        <th colspan="2">ACTIONS</th>
    </tr>
    </thead>
    <tbody>
    <tr th:each="u,loop:${users}">
        <td scope="row" th:text="${page * size + loop.count}"></td>
        <td th:text="${u.username}"></td>
        <td th:text="${u.email}"></td>
        <td th:text="${u.status ? 'ACTIVE' : 'INACTIVE'}"></td>
        <td>
            <th:block th:if="${u.status}">
                <a href="">BLOCK</a>
            </th:block>
            <th:block th:unless="${u.status}">
                <a href="">ACTIVE</a>
            </th:block>
        </td>
    </tr>
    </tbody>
</table>

<nav aria-label="Page navigation">
    <ul class="pagination">
        <!--       nút back (previous) -->
        <li class="page-item" th:classappend="${page == 0 ?'disabled':''}">
            <a class="page-link" th:href="@{'/users?page='+${page - 1}}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
                <span class="sr-only">Previous</span>
            </a>
        </li>
        <!--       danh sách các trang -->
        <li th:each="i:${#numbers.sequence(1,totalPages)}" class="page-item" th:classappend="${page+1 == i ? 'active':''}">
            <a class="page-link" th:href="@{'/users?page='+${i - 1}}" th:text="${i}"></a>
        </li>
        <!--       nút next -->
        <li class="page-item" th:classappend="${page + 1 == totalPages ? 'disabled' : '' }">
            <a class="page-link" th:href="@{'/users?page='+${page + 1}}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
                <span class="sr-only">Next</span>
            </a>
        </li>
    </ul>
</nav>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>