% 그래프 초기화

figure;

surf([], [], []);

xlabel('X');

ylabel('Y');

zlabel('Z');

title('3D 그래프');

% 데이터 업데이트

while true

    % 새로운 데이터 생성

    x = linspace(-10, 10, 100);

    y = linspace(-10, 10, 100);

    [X, Y] = meshgrid(x, y);

    Z = rand(size(X));

    % 그래프 업데이트

    surf(X, Y, Z);

    drawnow;

    % 1초 대기

    pause(1);

end
