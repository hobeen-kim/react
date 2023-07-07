package soccer.backend.restdocs.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EnumDocs {
    // 문서화하고 싶은 모든 enum값을 명시
    Map<String,String> authority;
}

