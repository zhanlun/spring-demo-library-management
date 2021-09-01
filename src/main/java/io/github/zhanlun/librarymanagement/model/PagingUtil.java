package io.github.zhanlun.librarymanagement.model;

import io.github.zhanlun.librarymanagement.book.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class PagingUtil {
    public static Pageable getPageable(Map<String, String> allRequestParams) {
        String _start = allRequestParams.get("_start");
        String _end = allRequestParams.get("_end");
        int start = _start == null ? 0 : Integer.parseInt(_start);
        int end = _end == null ? 10 : Integer.parseInt(_end);
        int pageSize = end - start;
        int pageStart = start / pageSize;
        String _sort = allRequestParams.get("_sort");
        String _order = allRequestParams.get("_order");
        boolean isDesc = _order != null && _order.equalsIgnoreCase("DESC");
        _sort = _sort == null ? "id" : _sort;
        Sort sortBy = Sort.by(_sort);
        sortBy = isDesc ? sortBy.descending() : sortBy.ascending();
        return PageRequest.of(pageStart, pageSize, sortBy);
    }
}
