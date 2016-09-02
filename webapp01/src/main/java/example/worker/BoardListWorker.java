package example.worker;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.dao.BoardDao;
import example.vo.Board;

@Component("/board/list.do")
public class BoardListWorker implements Worker {
  @Autowired
  BoardDao boardDao;
  
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int pageNo = 1;
    int length = 5;
    
    if (request.getParameter("pageNo") != null) {
      pageNo = Integer.parseInt(request.getParameter("pageNo"));
    }
    
    if (request.getParameter("length") != null) {
      length = Integer.parseInt(request.getParameter("length"));
    }
    
    HashMap<String,Object> map = new HashMap<>();
    map.put("startIndex", (pageNo - 1) * length);
    map.put("length", length);
  
    List<Board> list = boardDao.selectList(map);
    request.setAttribute("list", list);
    
    return "/board/BoardList.jsp";
  }

}










