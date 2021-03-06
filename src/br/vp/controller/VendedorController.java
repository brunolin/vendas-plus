package br.vp.controller;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.vp.dao.VendedorDAO;
import br.vp.dao.hibernate.VendedorDaoHibernate;
import br.vp.dto.*;
import br.vp.model.*;
import br.vp.controller.*;


/**
 * 
 * @author Brunolin
 *	Classe de controlle que � a ponte entre a Bean e a DAO
 */
public class VendedorController {
	
 	VendedorDAO vendedorDAO;
 	VendedorDaoHibernate vendedorHibernate;
 	MailController mail;
 	
	public VendedorController() {
		vendedorDAO = new VendedorDAO();
		vendedorHibernate = new VendedorDaoHibernate();
		mail = new MailController();
	}

	public boolean cadastroVendedor(VendedorDTO vendedorDTO){
		Vendedor vendedor = new Vendedor();
		
		vendedor.setNome(vendedorDTO.getNome());
		vendedor.setCidade(vendedorDTO.getCidade());	
		vendedor.setCpf(vendedorDTO.getCpf());
		vendedor.setEmail(vendedorDTO.getEmail());
		vendedor.setEstado(vendedorDTO.getEstado());
		vendedor.setPontos(0);
		vendedor.setSenha(vendedorDTO.getSenha());
		vendedor.setTelefone(vendedorDTO.getTelefone());
		
		boolean retorno = vendedorDAO.cadastroVendedor(vendedor);
		
		if(retorno) {
			String mensagem = "<img style=\"width: 150px;height: 150px;\"src=\"data:image/png;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/4RDmRXhpZgAATU0AKgAAAAgABAE7AAIAAAAJAAAISodpAAQAAAABAAAIVJydAAEAAAASAAAQzOocAAcAAAgMAAAAPgAAAAAc6gAAAAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEJydW5vbGluAAAABZADAAIAAAAUAAAQopAEAAIAAAAUAAAQtpKRAAIAAAADNTUAAJKSAAIAAAADNTUAAOocAAcAAAgMAAAIlgAAAAAc6gAAAAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADIwMTc6MTE6MjIgMDA6Mjc6NTEAMjAxNzoxMToyMiAwMDoyNzo1MQAAAEIAcgB1AG4AbwBsAGkAbgAAAP/hCxtodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvADw/eHBhY2tldCBiZWdpbj0n77u/JyBpZD0nVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkJz8+DQo8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIj48cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPjxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSJ1dWlkOmZhZjViZGQ1LWJhM2QtMTFkYS1hZDMxLWQzM2Q3NTE4MmYxYiIgeG1sbnM6ZGM9Imh0dHA6Ly9wdXJsLm9yZy9kYy9lbGVtZW50cy8xLjEvIi8+PHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9InV1aWQ6ZmFmNWJkZDUtYmEzZC0xMWRhLWFkMzEtZDMzZDc1MTgyZjFiIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iPjx4bXA6Q3JlYXRlRGF0ZT4yMDE3LTExLTIyVDAwOjI3OjUxLjU0NjwveG1wOkNyZWF0ZURhdGU+PC9yZGY6RGVzY3JpcHRpb24+PHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9InV1aWQ6ZmFmNWJkZDUtYmEzZC0xMWRhLWFkMzEtZDMzZDc1MTgyZjFiIiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iPjxkYzpjcmVhdG9yPjxyZGY6U2VxIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+PHJkZjpsaT5CcnVub2xpbjwvcmRmOmxpPjwvcmRmOlNlcT4NCgkJCTwvZGM6Y3JlYXRvcj48L3JkZjpEZXNjcmlwdGlvbj48L3JkZjpSREY+PC94OnhtcG1ldGE+DQogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgIDw/eHBhY2tldCBlbmQ9J3cnPz7/2wBDAAcFBQYFBAcGBQYIBwcIChELCgkJChUPEAwRGBUaGRgVGBcbHichGx0lHRcYIi4iJSgpKywrGiAvMy8qMicqKyr/2wBDAQcICAoJChQLCxQqHBgcKioqKioqKioqKioqKioqKioqKioqKioqKioqKioqKioqKioqKioqKioqKioqKioqKir/wAARCADNAMUDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD6RooooAKKKKACiiigAooooAKKKCcdaACivA/Hnxi8Qa/4rfwb8Jrc3V0jGOe+QBgCOu0ngAd2P4VzOufCq+0q3t7z4l/FP+zru7J8uPfJJ83fByOnqBigD6ior5luPDfxQ+GWnxeIPCviNvE2ibBI8eWf931yUbPGO6mpdP0gfF6ybxF8Otbm8M+IYD/p+nG4cRM/Zlx0B9cUAfStFfP3gr4weJPCfiqPwh8W7dopHISHUGA4PQFiOGU/3h0719AKwZQykEEZBHegBaKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAK4P40eJJvC/wq1W8s22XEyC3iYHlS5wSPwzXeV5D+0x/wAkik/6/Iv5mgCX9njwbbeH/hva6s8am/1cefJLjkR5+Rc/Tn8a4Sa0HxR/amns9TJl0vQlLeSfukR44/F2Ga9p+F3/ACSnw1/2Dof/AEEV5D8Hf+TlPG//AFyn/wDR6UAfQ4jRYxGqqEA2hQOMelfOGoWSfC39qDTX0kCDS/EBCyQLwq+YdpAHs+GH1xX0jXzx8df+S2+A/wDrvF/6OWgDufj14LtvFPw3vbxYR/aOlIbm3lA+bA++ufQjP4gVa+BfiWXxP8JtNuLpi9xalrSViclih4P/AHyVrpvHP/Iga7/14Tf+gGvN/wBl3/klE/8A2Epf/QUoA9mooooAKKKKACiiigAooooAKKKKACiiigAqrf6pYaVCs2p3tvZxsdoeeQICfTJq1Xh/7U//ACTrT/8Ar/H/AKCaAPVv+Ez8M/8AQw6X/wCBaf40f8Jn4Z/6GHS//AtP8a8c8Pfs0eD9V8M6XqFxfasst3ZxTuEmQAMyBjj5OmTWj/wyz4K/5/8AWP8Av8n/AMRQB6l/wmfhn/oYdL/8C0/xo/4TPwz/ANDDpf8A4Fp/jXlv/DLPgr/n/wBY/wC/yf8AxFH/AAyz4K/5/wDWP+/yf/EUAepf8Jn4Z/6GHS//AALT/GvKf2ivEWi6n8K5LfTtWsrqb7XEfLhuFdsZPOAak/4ZZ8Ff8/8ArH/f5P8A4ij/AIZZ8Ff8/wDrH/f5P/iKAOr+G3ivw9a/DHw7Bc65p0M0enxK8b3SBlO0cEZ4ryn4S63pdn+0N4yvLvUbWC1mimEc0kyqj5mQ8EnB4rq/+GWfBX/P/rH/AH+T/wCIo/4ZZ8Ff8/8ArH/f5P8A4igD1L/hM/DP/Qw6X/4Fp/jXgnxr1vS7/wCMfgi5stRtbiCGaMyyxTKyp++U8kHjiup/4ZZ8Ff8AP/rH/f5P/iKP+GWfBX/P/rH/AH+T/wCIoA7bxp4t8Oz+Btbig13TZJHsZVVFukJY7DwBmvPf2bPEOjaX8MJ4NS1WytJTqEjCOedUbG1OcE1c/wCGWfBX/P8A6x/3+T/4ij/hlnwV/wA/+sf9/k/+IoA9S/4TPwz/ANDDpf8A4Fp/jR/wmfhn/oYdL/8AAtP8a8t/4ZZ8Ff8AP/rH/f5P/iKP+GWfBX/P/rH/AH+T/wCIoA9S/wCEz8M/9DDpf/gWn+NH/CZ+Gf8AoYdL/wDAtP8AGvLf+GWfBX/P/rH/AH+T/wCIoP7LPgoKT9v1jp/z2T/4igD1/T9Z0zVg50vULW88vG/7PMr7c+uDxV2vnf8AZYhW3m8VQpkrHPGgJ64G4V9EUAFFFFABRRRQAUUUUAFeH/tT/wDJOtP/AOv8f+gmvcK8P/an/wCSdaf/ANf4/wDQTQB6r4L/AORC0D/sGW3/AKKWi38Z+GrrV/7Kttd0+XUN5T7Klwpk3DqNuc5o8F/8iFoH/YMtv/RS18r62P8AhE/2qfM5RP7VSTP+zJj/AOKoA+utQ1Gz0qxkvdTuorS1iGZJpnCqo9yazU8Z+GpdHk1WPXtPbT4nEcl0LhfLVj0BbOM1wH7SOpfYvhDPCpwbu4ji+ozn+leWLp4s/wBjeebGDd6ikp9/3oX+lAH0L/wszwR/0Nmj/wDgYn+NaGmeLvD2tR3D6RrVjepapvnaCdXEa+rYPA4r5U+FHwPt/iT4Xn1abWZLForloPLSIMDgA5zn3r2bwl8IYfhj4Z8USw6rJf8A23TpFIeMLt2ox/rQB3th488J6rfRWWm+I9MurqY4jhiukZnPoADzVrWPFOg+HpYo9d1iy095gWjW5nVC4HUjPWvgbwzrE3h/xVpmr2xxJZXUcw9wGBI/EZFe2ftTXEd3rHhW5hOY5rN5EPqCykUAfR2p+KNC0W1gudX1ezsoLn/UyTzKiycZ4J68VnxfEfwXNKscXirSGdjgAXicn868N/aO/wCSb+Cv93/2ktczpXwC/tj4RL4wtdZZbhrKS7+ytENp2Zyu7PotAH13HIksayROrowyrKcgj61naz4l0Tw6sLa9qtppwnJERuZlj34xnGeuMj868J/ZW8T315b6v4fu53lt7REntw5z5YJIZR7dDWF+1FdS6n8QtD0S2y7x2gKpn+ORyB/6CKAPpLRvE2h+IfN/sLVrPUfJx5n2aZZNmemcdKv3V1BZWslzeTRwQRrueWVgqqPUk9K+Wv2WNRaz8c6vpUvH2i13YPZkb/69af7VPiS9GoaT4cgleO0aI3MyK2BI2cLn1Ax+tAHt1l8TfBOo6gtlZ+J9OluGbaqCYDcfQE8GuoblD9K+QPGnwLXwp8K7XxVDqklzdFYXmgCfKBJj7p68ZFe5fALxDf8AiH4TW7atI8tzZTPa+ZJ95lUArn1wGA/CgDif2X/+P/xf/wBfSfzavoSvnv8AZf8A+P8A8X/9fSfzavoSgAooooAKKKKACiiigArw/wDan/5J1p//AF/j/wBBNe4V4f8AtT/8k60//r/H/oJoA9V8F/8AIhaB/wBgy2/9FLXzF+0hbNpPxns9SVdqz20E4b1KMQf/AEEV9O+C/wDkQtA/7Blt/wCilrwv9rPTQYPDmpgchprdj9QrD+RoAf8AtO62k/gnwxbRNkXjfafqoQf/ABVXPHuljR/2QtNs9u1lhtHcf7TuHP6sa8u+I+qP4ptfh5YRsZJW0iGMqP77SGP/ANkr3b9oO2Wz+A9xax/chkto1+gYD+lAHl3wT+M3hr4e+DbjS9di1B7iW7aZTbQqy7SoHUsOeK96tfF+neOfhXqWuaMk6Wk1pcoouECvlVYHgE9x6143+z/8NPCPjHwJdX/iTR0vrlL1o1kaWRcKFU4wrAdzXt0/hzSvCvw41TStAtFs7KOzuGSJWZgCyMTyxJ60AfFOi+HjqvgfxDqUQzLpTQSnj+BmKt+pB/Cuh+IWvf2/4H8BzO++W3s5raTnkFHA/liur/Z20aLxDpnjXSLgZS904Q/Qndg/gcH8K8ZuWuYHGnXBOLSZxsP8LZAb+VAH0D+0d/yTfwV/u/8AtJa9C8Bf8mvw/wDYFuf/AEGSvPf2jv8Akm/gr/d/9pLXoXgL/k1+H/sC3P8A6DJQB5f+yd/yNmv/APXin/oyofEkn/CUfteQQEb4ra7hhA68RoCf1yal/ZQYJ4q8Qs3RbBCf++653wV4u0TT/wBoLUPEviO6NvaLPcOj7S2WJIUcUAW/hoT4X/ahm06b5Q19dWntyWK/yFehftN+A7zWdJsvE+lQNO+nKYrtEGSIich8d8HOfrXk3inxTpNx+0TD4m8PXPnWL6hbXAk2leRsD8H3Br6x17x14V0C8XTvEGr2trPMgIhmz86nj06GgDyn4F/F/T9f0a28IeKGRNRt1EdtJNgpcoOg56MP1r3SOCG3hZLeJIl5O1FCj9K+Vfjz8K7fwjPD4w8KMYLC5mHmQxnH2eQ8qyHsp/Q17h8GfGVx42+GFnqGoHdewFrW4f8AvsmMN9SCCffNAHnX7L//AB/+L/8Ar6T+bV9CV89/sv8A/H/4v/6+k/m1fQlABRRRQAUUUUAFFFFABXh/7U//ACTrT/8Ar/H/AKCa9wrw/wDan/5J1p//AF/j/wBBNAHqvgv/AJELQP8AsGW3/opa86/aa0sX/wAJTcgZexvY5gfY5Q/+hfpXovgv/kQtA/7Blt/6KWtS8srXUbVra/tormB/vRTIHVvqDxQB8NfCuKfW/iv4WtZ2MqW1ymwH+FEYyY/PP519MftH/wDJFtQ/6+IP/Rgrv7PwtoGnXS3On6Jp9rOn3ZYbVEZfoQM1dvtPs9TtWttRtYbuBiCYp4w6kjpweKAPjz4X/HCX4beGptJj0ZL4S3Bn8xpiuMgDGMe1ez+Cvi9J8T/DXiqKTSlsPsWnOwKy7925GHp7V6R/whXhb/oXNJ/8Ao/8KT7P4U8NQznydJ0uOVds2EjiDr6N0yKAPn/9k7/kM+If+uEX/oRrzj406D/wj/xe1eBIykVxKLmPjgh+ePxzX1PbeN/hh4dlk/s/VdCsXfhzahFLfXaOaf8A8JR8MvFN8jzX2gahckBFa4WNnx2ALCgDyL9o7/km/gr/AHf/AGktchpnx6vdK+FS+DbLR494tHtRdtIScPnJ2464Y19bXWjaPq9rCl7p9lewRD9yssKyKnb5cjA/Cq0Xg/w1BKskPh/S43U5DLZxgj9KAPAPgJ4d1Dw14F8V+LdRge2hmsWS28wbS6qGYtj0ziuW+BHw00j4j32tz+I0na3tRH5Zik2ZdyxPP0H619eTWsFzavbXEMcsDrtaJ1BVh6EdMVBp2j6bo8bppOn2tkkhy628Kxhj6nA5oA+QPjv8OdJ+Hmt6Wnh5ZktrqBmbzZNx3q3Y/Qitz456PqGq6H4T8c2cLz202nQx3DopYROAGBPoDzz7V9Q6joelawUOrabaXpjzs+0QLJtz6ZHFTpYWkdgLJLWFbQJsEAjGwL6bemKAPj7x78crvx54EtfDI0dbeTdGZpVctvKdAoxxk1738BPC174V+FFvDqsTQXV7K920TdUDABQfQ4UHHvXYWngfwvYXou7Pw/p0NwpyJEtlBB9RxxW433D9KAPnz9l//j/8X/8AX0n82r6Er57/AGX/APj/APF//X0n82r6EoAKKKKACiiigAooooAK8P8A2p/+Sdaf/wBf4/8AQTXuFeH/ALU//JOtP/6/x/6CaAPVfBf/ACIWgf8AYMtv/RS1t1ieC/8AkQtA/wCwZbf+ilrjPjL8XIfhvpkNtYxpcazeKWgjb7sSjje349B3waAO81vxDpPhuwa81zUILKAD70zgZ9gOpP0rxXxX+1Lo9gzweFNMk1KQcCe4Plx/l1P6V45pfhr4gfGnXXvpDcXql8Pe3JKwQj0Xt+C17l4M/Zm8N6MIrjxRK2s3S8mLJSEH6DlvxoA8du/ih8VfiJcvb6XNeGNzg2+lQFFX2LDn8zWV4q+Gni3w/oB1rxldR2okbbFDcXJeaVvQKM19l3c2h+B/DNxdmG303TbKMuywxhFAHYAdzXyp5muftD/FpUcyQ6VAeFH3bW3B/wDQm9fX6UASfCX4Er8Q/DsusanqVxp1uJjHCI4Q3mgdSCT68V6I/wCyfoHl/uvEepLJ2YxxkflgV7hpOlWeh6Pa6ZpcCwWlrGIoo17KKuUAeB6Ro3iz4H+ItPFzrDa14S1C4W2lDghrZ2OFbBJxz6HBr3zrXmnxd1BL2fw74QtsSX2rajFIUB5SGNgzOfQcYr0sDAAoAKKZNNHbwPNPIscUalndzgKB1JNeIat4+1v4s+JpvCfw3lez0iE41HWlGDs6YQ9s847mgD0fVfiZ4V0e/azutSDyxuqTGBDIsBY4G9l4Xk966uvHB4Y0ifUbP4ceE7cf2Zp00d5r92PmaRlIZY2bu7EAn0Fex0AFI33T9KWkb7p+lAHz5+y//wAf/i//AK+k/m1fQlfPf7L/APx/+L/+vpP5tX0JQAUUUUAFFFFABRRRQAV4f+1P/wAk60//AK/x/wCgmvcK8Z/aZ02+1TwBYxabZz3ci3wYpDGXIG084FAHpfgv/kQtA/7Blt/6KWsXxV8KfDPjPxVZ67r9vJcT2sPkiLfiORQSRuHfBJrd8IRvD4H0KKZGSRNOt1ZWGCpEa5BFbFAENpZ29hax21lBHbwRjakcShVUewFTE4GT0orx/wCP/wAT/wDhDfDJ0bSJgusakhUMp5gi6M31PQfnQB5j8dPiDeePfF8PgvwsXnsoJxGyxf8ALzPnH/fK/wCJr3f4V/Duz+HXhGKyjCyahcASXtxjl3x0HsOgrzb9nH4XjT7L/hM9dhLXl0uLCNx/q0PWT6t29B9a98nnitoXmuJUiiQZZ3YAKPcmgCSuV8ffEHRvh/oL32qzK1wwItrRT+8nfsAPT1PauA8cftCWNlcHRfAFsdd1mU+XHIilokbp0HLn6ce9R+Afg9qWp60vjD4rXLajqrEPb2LnMcHuw6fRRwPegC98JPC2saxrd18R/G6ldT1BdlhasP8Aj1g7cds9h6c969clljgheWZ1jjRSzOxwFA6kmngBQAowBwAK+cPjV8RtQ8W+II/h14ELTvLL5V5JCf8AWv8A88wf7o/iNAEfjTxrrXxo8Y/8IP4Cd4tER8Xt6uQJFB5Yn+56Dua7iOKHwrZW/wAN/heitqjKG1DUNuVs1PWWQ93PZazPDWit4L0oeBPh+sdz4jnUNrGsFcx2WepJ7tjhV/E16l4U8Kaf4R0kWenhpJZDvubqU5luJD1dj3P8qAF8J+FdP8IaGmnaarMSxknnkOZLiQ/edj3JNbdFFABQRkYoByMjkUUAcz4R+H+geCJL1/D9vJC184ebfIWyRn16da6aorm5gs7WS4u5UhhiUs8kjYVR6k1wOhfGzwl4j8c/8Ixpc08lw+RDceX+6lYAkgHr0B5xQB6HRRRQAUUUUAFFFFABRjPWiigAoorxr4sfHO78AeIv7E0zQvtcohWRrmZiEy3QAAc4+tAHp/ijxHY+EvDV5rWqPtt7WMsRnlz2Ue5PFfI/hqAfFb4mXnijxxfw2Gi28omupJ5QiBR9yBSfYY9cZNZ3jD4peMPiqbbRp4VdDLuis7KI5kftxyTW74W/Zt8aa4yPrIh0O0PJ+0NvkP0Re/1IoA9Q8V/tLeGNBtzZeEbVtUliXZG4HlwKBwMdyPoK4S20n4p/He4S41W4k0vQGbcmVMUGP9lesh9zn61674L+APg/wm0dzcW51e+TnzrsAqD6hOn55r09VCKFQBVAwABgCgDivh98KPDnw8tP+JZb/aL9xiW+nAMjew/uj2FdtRWP4q8T6b4P8O3OsazOsMECkgE8yN2VR3JoA88+PXxP/wCEJ8N/2TpUwGtakhCbT80EfQv7E8gfj6V5z8IPCOp2SyxaIoTxHeoPtuoyruXSIW5289ZmHOO3Ga5Xwt4d1z47fFK81e8meC0SQSXFxjPkoD8ka++Bx+Jr648P+H9O8M6RFpukW4hgTknq0jHqzHqWPcmgCLwx4Y07wno6afpiNjJeaeQ7pJ5D953buSa8/wDjD8abTwDavpmjGO716RfufeW2BHDP79wK0vH3xDOnpeaZ4fmjWa1iMmpam/MOnR/+zSt0VB3614X8NPAE3xZ8ZTavqUc0fh2zlLO8py90+c4Ld2PVj2GBQB6b+z6PGmtW974p8W6xe3FleDbawXD/ACvg8yBew7DFYfxQ+LuqeKfEC+Avhk7STXEnkXF7Afmc/wASo3ZRzlvY1H8afiuQV+H3w9y0jBbW4ktB07CGPH5HH0rvfgx8Jrf4e6ILzUUSTXbuMefJ18lTz5an+Z70Add4C8My+EPBOn6LcXkl5Nbp+8lkYnLE5IGewzxU3ifxbpvhW1ie+Z5rq4bZa2Vuu+a5f+6ijk/XoK5T4gfFeHw840jwzb/2vrs0qwKif6m2d+F8x+gJ7L1qD4ZtYTvqmqeJFmTxZYOYtUk1B1JtxjI8sj5ViI5GPxoAzPGK3LeGrnxZ8UXW302zXfZeHYpPkklP3BMR/rGz/D0FcB+zp4RvPEPje88eajGIra3eQW4C4V5XBB2+yqcfiKi8YavqXx++Jtv4b8NM48P6fIWkucfJgcNKf5KK+k/D2g2PhjQLTSNKiEVraxhEA7+pPuTzQBpUUUUAFFFFABRRRQAUUUUAFecfG7xXYeEfAFxdTQW82o3WbeyWWMMQxHLDPoOfyr0YkKpLHAAySe1fIfjzU7z40/HG30PSpGbT7eT7NBg/KqKcySfjj9BQB1n7MngAubjxrqkR+YtDYBl6/wB+Qfy/OvpGqWj6Va6HotppenxiK2tIlijQdgBV2gAooqrqep2ej6Zcahqc6W9rboXllc4CgUAQa7run+G9Gn1TV7hYLaEZLHqx7KB3J6AV8qfG/U9e8T6xo8OopLDcag3mWWkg/wDHvETtQuO8jnJPoABXumg6ddfETXLfxX4ht3h0a1bfo2mzDG/0uJF9T/CD06153ocNp4m/aj1/WtbnijsvDybkM7hVVlwi9ew+Y/hQB6/8M/BNt4D8DWWkwoPtBUS3cmOZJSOT+HQewqh4j8Sal4g1aTwp4HmEdyp26jquNyWCd1X1lI6Dt3qO58Raj4/MmneCXltNKJKXOulMBh3W3B+8f9voK63w/wCHtN8MaPFpujW4gt4xzzlpG7szHlmPUk0AfOPx+jtfDVp4f8C6RIbazuG+13s0jZady20PI3c/eNei6DGdX8OWXhD4dmS10C1jCX+uKu3zf7yQ/wB52Oct0Fd14j8A+GPFt/a3niLSIb6e0GImkzwM5wQDyM9jW9b20FnbpBaQxwQxjCRxqFVR6ACgD5d+F39g6L8Ttf1fX9Ou4buxmaHTLCGzeRlAJGRgctgDk9yTXs7S+L/HREUUEvhPQ2/1ssuDfXC+igcRA+pya70RRhy4RQx6sBzTqAOJ174aabeeA5PD+g40yVJFube5HzMJ0OVdj1Y56mvLfF/ws+Kfi++lnnudHsXntUtrxrS4kVb0ITtZxjrzX0RRQBwfwj8CnwH4OWwu7G1t79mJuJoJjL5x7EkqMfTtXeUUUAFFFFABRRRQAUUUUAFFFBIAJJwB1NAHl3x78e/8IZ8P5bazkC6nqubeAA8omPnf8BwPc1zf7M/gEaP4cm8V6hFi91IbLcMOUhB6/wDAj+gFedeJZ7n43/H6PS9Pdn0u2l8lXH3UgQ/O/wCJzj6ivrOys4NOsILOzjEcEEYjjQfwqBgCgCeiiigCC9vrXTbKW7v547e3hUtJLI2FUDuTXnttZ3fxS1SLUtVt5LXwlayb7KzlGG1FgeJpF7R/3VPXqa6PWfBVt4i8QRX2t3lxdWNuFMOlk7YA4/jcD759AeBWxqOraZoVn52qXtvYwKMBpnCAD2zQBeAwMDgVxd18I/BV94nm1++0SK5vZ23yeaSyFvXZ0zXJeJ/2lvBmiCSLSVuNauV4UQDZET7ue30Brze5+PfxK8ZSm38HaItqpOB9lgadx/wIjH6UAfUsaQ2sCRxKkMUahVVQFVQOwHas3UvFfh/R03arren2g/6bXKKfyJr5zh+Ffxl8bMsviTXX0+CTlhc3RyB/uJ/I4rrNI/ZY0CF1l8Qa5f6nJ1YRqIVJ/wDHj+tAHa33x0+HNhnf4lgmI7QRPJ/Jawbn9pXwWr7dPtNZ1A9jBZ4B/wC+mB/Sul0r4L+AdHKtbeHbaR16NcZkP/jxrrbPRtM09Atjp9rbhenlQquPyFAHmtp8brvVMHSPh34muVb7rmAKp/HpWrB488Z3R/c/DPUUB7z30Mf8zXoVFAHJWmueNbn/AF3g63tP+u2qof8A0FTW1Zz63JKovrCyhTPzGK7ZyPwKCtOigAooooAKKKKACiiigAooooAK83+OnjJvB/wzvDaymO+1DNrbspwV3D5mH0XP516RXC/Er4X2XxK/shdQvJbePTpzIyIMiVWxuX2Py9fegDjf2a/Av9g+EJPEV7Fi91bBiLDlYR0/M8/lXtlR28EVrbRW9uixxRIERFGAqgYAH4VJQAVg+KvG3h/wXY/avEWpRWoP3IycySf7qjk14/8AFX9ouLRbq50PwSFuLyImOa+cZjjYdQg/iI9en1rxnwv4J8Z/GLxFLdtJNcBm/wBJ1K8Y7E9ge59FH6UAeieNP2odSvWez8D2C2aElRd3C75D/up0H45rmtE+FHxJ+KFymo+ILi5gtpTu+06nI2SP9lOv6AV774A+CPhbwMsVz9mXUtUXn7ZcqDsP+wvRfr1r0igDyLwh+zh4O8PCOfWEk1y8Xkm54iB9ox1/HNeqWWnWWmwCHT7SG1iXokMYQD8BVmigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigApCAykHoRg0tFAHgqfsu6XJ41n1G+1iSbSHlMq2Sx7ZDk52l89PcDNe36XpVjoumxWGlWsdrawrtSKJcACrdFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAH/2Q==\"/><h1 style=\"display: inline-block;\">Ol� " + vendedor.getNome() + ", seja bem vindo ao Vendas Plus!</h1><br><br><h2>Aproveite nossas campanhas e boas vendas.</h2>";
			return mail.sendMail(vendedor.getEmail(), mensagem);	
		}
		
		return false;
	}
	
	public List<ProdutoDTO> getCampanhas(){		
		ProdutoDTO produtoDTO;
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();
		
		List<Produto> produtos = vendedorHibernate.getCampanhas();
		
		for(Produto produto : produtos) {
			
			produtoDTO = new ProdutoDTO();
			produtoDTO.setNomeProduto(produto.getNomeProduto());
			produtoDTO.setPontosRecompensa(produto.getPontosRecompensa());
			produtoDTO.setInicioCampanha(produto.getInicioCampanha());
			produtoDTO.setIdEmpresa(produto.getIdEmpresa());
			produtoDTO.setIdProduto(produto.getIdProduto());
			
			String image64 = ImageController.getBase64FromResource(produto.getImg());
			produtoDTO.setImg(image64);
			
			produtosDTO.add(produtoDTO);
		}
		
		return produtosDTO;
	}
	
	public VendedorDTO getInfoVendedor(LogadoDTO login) {
		try {		
			VendedorDTO vendedorDTO = new VendedorDTO();
			Vendedor vendedor = vendedorDAO.getInfoVendedor(login.getUsername());
			
			vendedorDTO.setNome(vendedor.getNome());
			vendedorDTO.setPontos(vendedor.getPontos());
			vendedorDTO.setIdVendedor(vendedor.getIdVendedor());
			
			return vendedorDTO;
		} catch (NullPointerException e) {
			System.out.println("Salve");
		}
		
		return null;
	}
	
	public VendedorDTO getInfoVendedorByEmail(String email) {
		try {		
			VendedorDTO vendedorDTO = new VendedorDTO();
			Vendedor vendedor = vendedorDAO.getInfoVendedorByEmail(email);
			
			vendedorDTO.setIdVendedor(vendedor.getIdVendedor());
			vendedorDTO.setNome(vendedor.getNome());
			vendedorDTO.setPontos(vendedor.getPontos());
			vendedorDTO.setCidade(vendedor.getCidade());
			vendedorDTO.setEstado(vendedor.getEstado());
			vendedorDTO.setEmail(vendedor.getEmail());
			vendedorDTO.setTelefone(vendedor.getTelefone());
			
			return vendedorDTO;
		} catch (NullPointerException e) {
			System.out.println("Erro no retorno de dados do vendedor por email");
		}
		
		return null;
	}
	
	public String cadastrarNota(VendasDTO vendaDTO) {
		Vendas venda = new Vendas();
		
		venda.setNomeProduto(vendaDTO.getNomeProduto());
		venda.setAprovada("F");
		venda.setData(vendaDTO.getData());
		venda.setIdEmpresa(vendaDTO.getIdEmpresa());
		venda.setIdProduto(vendaDTO.getIdProduto());
		venda.setIdVenda(vendaDTO.getIdVenda());
		venda.setIdVendedor(vendaDTO.getIdVendedor());
		venda.setImg(vendaDTO.getImg());
		
		String path = "c:\\git\\vendas-plus\\WebContent\\view\\img\\produtos\\";
		String newFileName = venda.getIdVendedor() + "-nota-" + venda.getNomeProduto() + "-" + venda.getImg();
		
		File oldfile = new File(path + venda.getImg());
		File newfile = new File(path + newFileName);

		if(oldfile.renameTo(newfile)){
			System.out.println("Rename succesful");
			venda.setImg(newFileName);
		}else{
			System.out.println("Rename failed");
		}
		
		return vendedorDAO.cadastroVenda(venda) ? "200" : null;
	}
	
	public List<VendasDTO> getNotas(int id) {
		VendasDTO vendaDTO;
		List<VendasDTO> vendasDTO = new ArrayList<VendasDTO>();
		
		List<Vendas> vendas = vendedorHibernate.getNotasVendedor(id);
		
		try{
			for(Vendas venda : vendas) {
				
				vendaDTO = new VendasDTO(); 
	
				vendaDTO.setNomeProduto(venda.getNomeProduto());
				vendaDTO.setData(venda.getData());
				vendaDTO.setIdProduto(venda.getIdProduto());
				vendaDTO.setIdVenda(venda.getIdVenda());
				vendaDTO.setAprovada(venda.getAprovada());
				
				vendasDTO.add(vendaDTO);
			}
			
			return vendasDTO;
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<BonusDTO> getBonus() {
		BonusDTO bonusDTO;
		List<BonusDTO> bonusDTOList = new ArrayList<BonusDTO>();
		List<Bonus> bonusList = vendedorHibernate.getBonus();
		
		for(Bonus bonus : bonusList) {
			bonusDTO = new BonusDTO();
			
			bonusDTO.setDescricao(bonus.getDescricao());
			bonusDTO.setIdBonus(bonus.getIdBonus());
			bonusDTO.setNome(bonus.getNome());
			bonusDTO.setPontosNecessarios(bonus.getPontosNecessarios());
			
			String image64 = ImageController.getBase64FromResource(bonus.getImg());
			bonusDTO.setImg(image64);
			
			bonusDTOList.add(bonusDTO);
		}
		
		return bonusDTOList;
	}
	
	public boolean resgatarBonus(ValidateBonusDTO bonus) {
		return vendedorDAO.resgatarBonus(bonus.getPontos(), bonus.getCpf());
	}
}
